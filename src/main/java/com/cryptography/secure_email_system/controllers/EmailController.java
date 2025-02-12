package com.cryptography.secure_email_system.controllers;

import com.cryptography.secure_email_system.DTO.ApiResponse;
import com.cryptography.secure_email_system.DTO.Email.EmailRequest;
import com.cryptography.secure_email_system.DTO.Email.EmailResponse;
import com.cryptography.secure_email_system.entities.Email;
import com.cryptography.secure_email_system.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/conversation")
    public ResponseEntity<ApiResponse<Object>> getConversation(@RequestParam Long senderId, @RequestParam Long receiverId) {
        try {
            List<EmailResponse> conversation = emailService.getConversation(senderId, receiverId);
            return ResponseEntity.ok(ApiResponse.success("Conversation retrieved successfully", conversation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Failed to retrieve conversation", e.getMessage()));
        }
    }


    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Object>> sendEmail(@RequestBody EmailRequest emailDTO) {
        try {
            Email email = emailService.sendEmail(emailDTO);
            EmailResponse emailResponse = new EmailResponse(email);
            return ResponseEntity.ok(ApiResponse.success("Email sent successfully", emailResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Failed to send email", e.getMessage()));
        }
    }
}