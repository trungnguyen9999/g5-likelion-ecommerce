package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.AdminRepository;
import com.likelion.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    public List<User> getAllUsersByAdminRole() {
        return adminRepository.findUsersByAdminRole();
    }

    public User getUserById(Integer id) {
        Optional<User> optionalEmployee = adminRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    public User saveUser(User user) {
        return adminRepository.save(user);
    }

    public User updateUser(User user) {
        return adminRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        adminRepository.deleteById(id);
    }

    public User findFirstByAccountId(Integer accountId) {
        return adminRepository.findFirstByAccountId(accountId);
    }
}
