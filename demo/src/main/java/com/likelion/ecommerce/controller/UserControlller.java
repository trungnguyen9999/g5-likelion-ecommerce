package com.likelion.ecommerce.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.likelion.ecommerce.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Validated
public class UserControlller {

	private final UserService userService;

    @GetMapping("/get-all-details")
    public ResponseEntity<List<UserDetailsDto>> getUserInfoDetails()
    {
        return ResponseEntity.ok().body(userService.getUserInfoDetails(null));
    }

    @GetMapping("/get-details/{account_id}")
    public ResponseEntity<List<UserDetailsDto>> getUserInfoDetailById(@PathVariable Integer account_id) {
        return ResponseEntity.ok().body(userService.getUserInfoDetails(account_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Integer id)
    {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok().body("Deleted user successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
