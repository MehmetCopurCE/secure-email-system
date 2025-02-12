package com.cryptography.secure_email_system.DTO;

import com.cryptography.secure_email_system.entities.User;

import java.util.Optional;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String publicKey;

    public UserDTO(Optional<User> user) {
        this.id = user.get().getId();
        this.email = user.get().getEmail();
        this.username = user.get().getUsername();
        this.publicKey = user.get().getPublicKey();
    }

    public UserDTO(Long id, String email, String username, String publicKey) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.publicKey = publicKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
