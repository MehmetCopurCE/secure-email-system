package com.cryptography.secure_email_system.controllers;

import com.cryptography.secure_email_system.entities.User;
import com.cryptography.secure_email_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Kullanıcıları almak için API endpointi
    @GetMapping
    public List<User> getAllUsers(@RequestParam Long currentUserId) {
        return userService.getAllUsers(currentUserId);
    }
}
