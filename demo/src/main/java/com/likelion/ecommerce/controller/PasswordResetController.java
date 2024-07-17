package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.PasswordResetToken;
import com.likelion.ecommerce.service.AccountService;
import com.likelion.ecommerce.service.EmailService;
import com.likelion.ecommerce.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PasswordResetController {

    private final AccountService accountService;

    private final PasswordResetService passwordResetService;

    private final EmailService emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        Optional<Account> accountOptional = accountService.findByEmail(email);

        if (!accountOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Email not found");
        }

        Account account = accountOptional.get();
        String token = passwordResetService.createPasswordResetTokenForUser(account);
        emailService.sendPasswordResetToken(email, token);

        return ResponseEntity.ok("Password reset email sent");
    }

    @PostMapping("/reset-password")
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
