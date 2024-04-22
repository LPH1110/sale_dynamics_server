package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.dto.LoginResponseDTO;
import com.pos.sale_dynamics.dto.RegistrationDTO;
import com.pos.sale_dynamics.service.AuthenticationService.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body)  {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    @GetMapping("/verify-user")
    public String verifyUser(@RequestParam String token) {
        return authenticationService.verifyToken(token);
    }
}
