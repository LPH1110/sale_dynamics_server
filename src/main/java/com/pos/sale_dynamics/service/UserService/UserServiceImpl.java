package com.pos.sale_dynamics.service.UserService;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.domain.Role;
import com.pos.sale_dynamics.domain.VerificationToken;
import com.pos.sale_dynamics.dto.UserDTO;
import com.pos.sale_dynamics.mapper.UserDTOMapper;
import com.pos.sale_dynamics.repository.RoleRepository;
import com.pos.sale_dynamics.repository.UserRepository;
import com.pos.sale_dynamics.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private UserDTOMapper userDTOMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<ApplicationUser> updateInfo(String username, UserDTO userDTO) throws UsernameNotFoundException {
        Optional<ApplicationUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            ApplicationUser founded = user.get();
            founded.setFullName(userDTO.fullName());
            founded.setEmail(userDTO.email());
            founded.setPhone(userDTO.phone());
            return Optional.of(userRepository.save(founded));
        } else {
            throw new UsernameNotFoundException("Username not found!");
        }
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> userDTOMapper.apply(user)).toList();
    }
    public ResponseEntity<UserDTO> findByUsername(String username) {
        Optional<ApplicationUser> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            System.out.println("In user service, user not found with username: " + username);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTOMapper.apply(user.get()), HttpStatus.OK);
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

    public String changePassword(String username, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        ApplicationUser user = userRepository.findByUsername(username).get();
        user.setPassword(encodedPassword);
        Calendar calendar = Calendar.getInstance();
        user.setChangedPasswordDate(calendar.getTime());
        userRepository.save(user);

        return "Password changed";
    }

}
