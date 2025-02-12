package com.cryptography.secure_email_system.services;

import com.cryptography.secure_email_system.entities.User;
import com.cryptography.secure_email_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(Long currentUserId) {
        return userRepository.findByIdNot(currentUserId);
    }
}
