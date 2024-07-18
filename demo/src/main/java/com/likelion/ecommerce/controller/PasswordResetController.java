package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.PasswordResetToken;
import com.likelion.ecommerce.service.AccountService;
import com.likelion.ecommerce.service.EmailService;
import com.likelion.ecommerce.service.PasswordResetService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PasswordResetController {

    private final AccountService accountService;

    private final PasswordResetService passwordResetService;

    private final EmailService emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email, @RequestParam String newPassword) throws MessagingException, UnsupportedEncodingException {
        Optional<Account> accountOptional = accountService.findByEmail(email);

        if (!accountOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Email not found");
        }

        Account account = accountOptional.get();
        String token = passwordResetService.createPasswordResetTokenForUser(account);
        emailService.sendPasswordResetToken(email, token, newPassword);

        return ResponseEntity.ok("Password reset email sent");
    }

    @GetMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<PasswordResetToken> tokenOptional = passwordResetService.getPasswordResetToken(token);

        if (!tokenOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        PasswordResetToken passwordResetToken = tokenOptional.get();
        Account account = passwordResetToken.getAccount();
        passwordResetService.changeUserPassword(account, newPassword);

        return ResponseEntity.ok("Password successfully reset");
    }
}
