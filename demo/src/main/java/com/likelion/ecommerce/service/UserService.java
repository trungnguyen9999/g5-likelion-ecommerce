package com.likelion.ecommerce.service;

import com.likelion.ecommerce.dto.UserDetailsDto;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.AccountRepository;
import com.likelion.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepo;
    private final AccountRepository accountRepository;

    public List<UserDetailsDto> getUserInfoDetails(Integer accountId) {
        return userRepo.getUserInfoDetails(accountId);
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
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            userRepo.delete(existingUser);
            accountRepository.deleteById(existingUser.getAccountId());
        } else {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
    }

    public User findFirstByAccountId(Integer accountId) {
        return userRepo.findFirstByAccountId(accountId);
    }

}
