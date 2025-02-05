package com.cryptography.secure_email_system.DTO;

public class LoginRequest {
    private String Identifier;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.Identifier = username;
        this.password = password;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        this.Identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
