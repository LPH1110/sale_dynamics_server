package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.domain.ApplicationUser;
import com.pos.sale_dynamics.service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/detail")
    public Optional<ApplicationUser> getInfo(@RequestParam String username) {
        System.out.println(username);
        return userService.findByUsername(username);
    }


}
