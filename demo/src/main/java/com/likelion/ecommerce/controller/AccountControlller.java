package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.service.AccountService;
import com.likelion.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountControlller {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getUserById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(accountService.getAccountById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody Account account)
    {
        accountService.updateAccount(account);
        return ResponseEntity.ok().body("Update account successfully!");
    }

}
