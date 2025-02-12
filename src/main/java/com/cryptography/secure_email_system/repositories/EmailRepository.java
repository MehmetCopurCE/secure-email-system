package com.cryptography.secure_email_system.repositories;

import com.cryptography.secure_email_system.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findBySender_IdAndReceiver_Id(Long senderId, Long receiverId);
}