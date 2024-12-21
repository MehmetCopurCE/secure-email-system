package com.cryptography.secure_email_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash; // Şifreyi hashlenmiş şekilde saklayacağız.

    @Column(nullable = false, unique = true)
    private String publicKey; // Kullanıcının public key'i
}