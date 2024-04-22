package com.pos.sale_dynamics.service.UserService;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.domain.Role;
import com.pos.sale_dynamics.domain.VerificationToken;
import com.pos.sale_dynamics.repository.RoleRepository;
import com.pos.sale_dynamics.repository.UserRepository;
import com.pos.sale_dynamics.repository.VerificationTokenRepository;
import com.pos.sale_dynamics.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;


    @Autowired
    private JavaMailSender mailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public List<ApplicationUser> findAll() {
        return userRepository.findAll();
    }
    public Optional<ApplicationUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public VerificationToken createUser(String fullName, String email, String password, String phone) {
        // Encode password
        String encodedPassword = passwordEncoder.encode(password.isEmpty() ? "123" : password);
        // Add role
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        // Create instance
        ApplicationUser newUser = new ApplicationUser(email, fullName, encodedPassword, authorities, email, phone);
        newUser.setEnabled(false);
        System.out.println("In user service, registering: " + newUser);

        ApplicationUser savedUser = userRepository.save(newUser);
        VerificationToken verifyToken = generateToken(savedUser);

        verificationTokenRepository.save(verifyToken);

        return verifyToken;
    }

    public String getVerifyToken(String username) {
        ApplicationUser user = userRepository.findByUsername(username).get();
        // find and delete expired token
        Optional<VerificationToken> oldToken = verificationTokenRepository.findByUserId(user.getId());
        VerificationToken verifyToken;

        if (oldToken.isPresent()) {
            verifyToken = oldToken.get();
            verifyToken.setToken(UUID.randomUUID().toString());
            verifyToken.setExpirationTime(verifyToken.getTokenExpirationTime());
        } else {
            // create new token
            verifyToken = generateToken(user);
        }

        verificationTokenRepository.save(verifyToken);
        System.out.println(verifyToken.getToken());
        return verifyToken.getToken();
    }

    public VerificationToken generateToken(ApplicationUser user) {
        // create token
        String token = UUID.randomUUID().toString();
        // generate verify token
        return new VerificationToken(token, user);
    }


}
