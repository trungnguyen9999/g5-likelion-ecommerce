package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public Account getAccountById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);
    }

    public Account updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getAccountId())
                .orElseThrow(() -> new NoSuchElementException("not found account with id: " + account.getAccountId()));
        if (account.getPassword() != null) {
            existingAccount.setPassword(encoder.encode(account.getPassword()));
        }
        if (account.getStatus() != null) {
            existingAccount.setStatus(account.getStatus());
        }
        if (account.getType() != null) {
            existingAccount.setType(account.getType());
        }

        return accountRepository.save(existingAccount);
    }

    public Optional<Account> findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return accountRepository.findById(user.getAccountId());
    }
}

