package com.cryptography.secure_email_system.controllers;

import com.cryptography.secure_email_system.DTO.*;
import com.cryptography.secure_email_system.entities.User;
import com.cryptography.secure_email_system.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.registerUser(registerRequest);
            RegisterResponse registerResponse = new RegisterResponse(user.getUsername(), user.getEmail());
            return ResponseEntity.ok(ApiResponse.success("User registered successfully. Please save your private key securely.", registerResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Registration failed", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOpt = userService.authenticateAndGetUser(loginRequest);
            if (userOpt.isPresent()) {
                UserDTO userDTO = new UserDTO(userOpt.get().getId(), userOpt.get().getEmail(), userOpt.get().getUsername()); // Hassas bilgileri gizlemek için DTO kullan
                return ResponseEntity.ok(ApiResponse.success("Login successful!", Map.of(
                        "id", userDTO.getId(),
                        "email", userDTO.getEmail(),
                        "username", userDTO.getUsername()
                )));
            }
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid username or password.", "The provided credentials are incorrect."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Login failed", e.getMessage()));
        }
    }



}