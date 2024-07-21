package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.PasswordResetToken;
import com.likelion.ecommerce.request.ForgotPassRequest;
import com.likelion.ecommerce.service.AccountService;
import com.likelion.ecommerce.service.EmailService;
import com.likelion.ecommerce.service.PasswordResetService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PasswordResetController {

    @Value("${application.frontend.url}")
    private String frontendUrl;

    private final AccountService accountService;

    private final PasswordResetService passwordResetService;

    private final EmailService emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassRequest forgotPassRequest) {
        Optional<Account> accountOptional = accountService.findByEmail(forgotPassRequest.getEmail());

        if (!accountOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Email not found");
        }

        Account account = accountOptional.get();
        String token = passwordResetService.createPasswordResetTokenForUser(account);
        try {
            emailService.sendPasswordResetToken(forgotPassRequest.getEmail(), token, forgotPassRequest.getNewPassword());
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Password reset email sent");
    }

    @GetMapping("/reset-password")
    public RedirectView resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<PasswordResetToken> tokenOptional = passwordResetService.getPasswordResetToken(token);
        RedirectView redirectView = new RedirectView();

        if (!tokenOptional.isPresent()) {
            redirectView.setUrl(frontendUrl + "/reset-password/fail");
            return redirectView;
        }

        PasswordResetToken passwordResetToken = tokenOptional.get();
        Account account = passwordResetToken.getAccount();
        passwordResetService.changeUserPassword(account, newPassword);

        redirectView.setUrl(frontendUrl + "/reset-password/success");
        return redirectView;
    }
}
