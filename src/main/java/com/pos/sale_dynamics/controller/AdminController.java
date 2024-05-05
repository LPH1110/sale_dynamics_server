package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.domain.VerificationToken;
import com.pos.sale_dynamics.dto.UserDTO;
import com.pos.sale_dynamics.service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping
    public String getInfo() {
        return "admin level access";
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public VerificationToken createUser(@RequestBody ApplicationUser userInfo) {
        return userService.createUser(userInfo.getFullName(), userInfo.getEmail(), userInfo.getPassword(), userInfo.getPhone());
    }

    @GetMapping("/generate-verify-token")
    public String generateVerifyToken(@RequestParam String username) {
        System.out.println(username + "\n");
        return userService.getVerifyToken(username);
    }
}
