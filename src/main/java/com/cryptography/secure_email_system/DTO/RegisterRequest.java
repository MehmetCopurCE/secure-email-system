package com.cryptography.secure_email_system.DTO;

public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private String publicKey;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String username, String password, String publicKey) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.publicKey = publicKey;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
