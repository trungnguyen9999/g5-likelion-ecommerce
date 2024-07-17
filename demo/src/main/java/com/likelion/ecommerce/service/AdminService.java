package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.AdminRepository;
import com.likelion.ecommerce.repository.UserRepository;
import com.likelion.ecommerce.response.UserWithRoleResponse;
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

    public List<UserWithRoleResponse> getUserWithRoles() {
        return adminRepository.findUsersWithRole();
    }
}
