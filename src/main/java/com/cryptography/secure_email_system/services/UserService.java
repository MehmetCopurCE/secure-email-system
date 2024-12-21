package com.cryptography.secure_email_system.services;

import com.cryptography.secure_email_system.entities.User;
import com.cryptography.secure_email_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String username, String password) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        // Şifreyi hashle
        String hashedPassword = passwordEncoder.encode(password);

        // RSA anahtar çifti oluştur
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        // Kullanıcıyı oluştur ve kaydet
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        user.setPublicKey(publicKey);

        userRepository.save(user);

        // Private key'i döndür (kullanıcıya yalnızca kendisi saklaması için veririz)
        return user;
    }

    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPasswordHash()))
                .orElse(false);
    }
}