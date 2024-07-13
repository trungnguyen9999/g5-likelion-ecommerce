package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> optionalEmployee = userRepo.findById(id);
        return optionalEmployee.orElse(null);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepo.deleteById(id);
    }

    public User findFirstByAccountId(Integer accountId) {
        return userRepo.findFirstByAccountId(accountId);
    }
}
