package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.PasswordResetToken;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final AccountRepository accountRepository;

    private final PasswordResetTokenRepository tokenRepository;

    private final PasswordEncoder encoder;

    public String createPasswordResetTokenForUser(Account account) {
        Optional<PasswordResetToken> existingToken = tokenRepository.findByAccount(account);
        String token = UUID.randomUUID().toString();
        existingToken.ifPresent(tokenRepository::delete);
        PasswordResetToken myToken = new PasswordResetToken(token, calculateExpiryDate(), account);
        tokenRepository.save(myToken);
        return token;
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 15);
        return new Date(cal.getTime().getTime());
    }

    @Transactional(readOnly = true)
    public Optional<PasswordResetToken> getPasswordResetToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Transactional
    public void changeUserPassword(Account account, String password) {
        account.setPassword(encoder.encode(password));
        accountRepository.save(account);
    }
}
