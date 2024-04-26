package com.pos.sale_dynamics.service.AuthenticationService;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.domain.VerificationToken;
import com.pos.sale_dynamics.dto.LoginResponseDTO;
import com.pos.sale_dynamics.repository.RoleRepository;
import com.pos.sale_dynamics.repository.UserRepository;
import com.pos.sale_dynamics.repository.VerificationTokenRepository;
import com.pos.sale_dynamics.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import com.pos.sale_dynamics.domain.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password.isEmpty() ? "123" : password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        ApplicationUser newUser = new ApplicationUser(username, encodedPassword, authorities);
        System.out.println("In authentication service, registering: " + newUser);
        return userRepository.save(newUser);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        System.out.println("Logging in: " + username + " - " + password);

        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateToken(auth);
            ApplicationUser user = userRepository.findByUsername(username).get();
            return new LoginResponseDTO(user, token);

        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return new LoginResponseDTO(null, "");
        }
    }

    public String verifyToken(String token) throws NoSuchElementException {

        try {
            VerificationToken verifyToken = verificationTokenRepository.findByToken(token).get();
            Date time = verifyToken.getExpirationTime();
            Calendar calendar = Calendar.getInstance();

            if (time.getTime() - calendar.getTime().getTime() <= 0) {
                verificationTokenRepository.delete(verifyToken);
                return "Token is already expired";
            } else {
                ApplicationUser user = verifyToken.getUser();
                user.setEnabled(true);
                userRepository.save(user);
                return "activated";
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Verification token not found");
        }
    }
}
