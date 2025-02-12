package com.cryptography.secure_email_system.services;

import com.cryptography.secure_email_system.DTO.Email.EmailRequest;
import com.cryptography.secure_email_system.DTO.Email.EmailResponse;
import com.cryptography.secure_email_system.entities.Email;
import com.cryptography.secure_email_system.entities.User;
import com.cryptography.secure_email_system.repositories.EmailRepository;
import com.cryptography.secure_email_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    public List<EmailResponse> getConversation(Long senderId, Long receiverId) {
        // Sender ve Receiver arasında geçen tüm e-posta mesajlarını alıyoruz
        List<Email> sentEmails = emailRepository.findBySender_IdAndReceiver_Id(senderId, receiverId);
        List<Email> receivedEmails = emailRepository.findBySender_IdAndReceiver_Id(receiverId, senderId);

        // Hem gönderilen hem de alınan e-postaları birleştiriyoruz
        List<Email> allEmails = new ArrayList<>();
        allEmails.addAll(sentEmails);
        allEmails.addAll(receivedEmails);

        // Tüm e-posta mesajlarını tarih sırasına göre sıralıyoruz
        allEmails.sort(Comparator.comparing(Email::getSentDate));

        return allEmails.stream()
                .map(EmailResponse::new) // EmailResponse constructor ile dönüştürüyoruz
                .collect(Collectors.toList());
    }



    public Email sendEmail(EmailRequest emailDTO) {
        User sender = userRepository.findById(emailDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(emailDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Email email = new Email();
        email.setSender(sender);
        email.setReceiver(receiver);
        email.setSubject(emailDTO.getSubject());
        email.setEncryptedContent(emailDTO.getContent());
        email.setSentDate(LocalDateTime.now());

        return emailRepository.save(email);
    }
}