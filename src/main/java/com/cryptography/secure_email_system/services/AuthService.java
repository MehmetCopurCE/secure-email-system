package com.cryptography.secure_email_system.services;

import com.cryptography.secure_email_system.DTO.LoginRequest;
import com.cryptography.secure_email_system.DTO.RegisterRequest;
import com.cryptography.secure_email_system.entities.User;
import com.cryptography.secure_email_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(RegisterRequest registerRequest) throws Exception {
        // Kullanıcı adı ve email kontrolü
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken.");
        }

        // Şifreyi hashle
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // Kullanıcıyı oluştur ve kaydet
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPasswordHash(hashedPassword);
        user.setPublicKey(registerRequest.getPublicKey()); // publicKey doğrudan RegisterRequest'ten alınacak

        userRepository.save(user);

        return user;
    }

    public Optional<User> authenticateAndGetUser(LoginRequest loginRequest) {
        // Önce username ile, sonra email ile kullanıcıyı bulmaya çalışalım
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getIdentifier());

        // Eğer username ile kullanıcı bulamazsak, email üzerinden arama yapalım
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(loginRequest.getIdentifier());
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Şifreyi kontrol et
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
                return Optional.of(user); // Kullanıcı doğrulandı, bilgilerini döndür
            }
        }

        return Optional.empty(); // Kullanıcı bulunamazsa veya şifre yanlışsa
    }

}