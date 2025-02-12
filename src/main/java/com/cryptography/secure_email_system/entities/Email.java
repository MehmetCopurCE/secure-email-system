package com.cryptography.secure_email_system.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private User sender;  // Gönderen kullanıcı

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    private User receiver;  // Alıcı kullanıcı

    @Lob
    @Column(nullable = false)
    private String subject;  // Şifrelenmiş konu

    @Lob
    @Column(nullable = false)
    private String encryptedContent;  // Şifrelenmiş içerik

    @Column(nullable = false)
    private LocalDateTime sentDate;  // Gönderilme tarihi

    // Constructor
    public Email() {
    }

    public Email(User sender, User receiver, String subject, String encryptedContent, LocalDateTime sentDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.encryptedContent = encryptedContent;
        this.sentDate = sentDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public void setEncryptedContent(String encryptedContent) {
        this.encryptedContent = encryptedContent;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }
}

