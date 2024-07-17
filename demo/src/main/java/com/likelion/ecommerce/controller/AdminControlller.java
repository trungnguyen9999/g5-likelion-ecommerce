package com.likelion.ecommerce.controller;

import java.util.List;

import com.likelion.ecommerce.response.UserWithRoleResponse;
import com.likelion.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminControlller {

    private final AdminService adminService;

    @GetMapping("/get-user-role")
    public ResponseEntity<List<UserWithRoleResponse>> getUserWithRoles()
    {
        return ResponseEntity.ok().body(adminService.getUserWithRoles());
    }

}
