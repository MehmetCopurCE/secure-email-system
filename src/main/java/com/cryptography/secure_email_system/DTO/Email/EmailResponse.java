package com.cryptography.secure_email_system.DTO.Email;

import com.cryptography.secure_email_system.entities.Email;

import java.time.LocalDateTime;

public class EmailResponse {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String subject;
    private String content;
    private LocalDateTime sentDate;

    public EmailResponse() {
    }

    public EmailResponse(Email email){
        this.id = email.getId();
        this.senderId = email.getSender().getId();
        this.receiverId = email.getReceiver().getId();
        this.subject = email.getSubject();
        this.content = email.getEncryptedContent();
        this.sentDate = email.getSentDate();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }
}
