package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.response.UserWithRoleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<User, Integer> {
    User findFirstByAccountId(Integer accountId);

    @Query("SELECT u FROM User u JOIN Account a ON u.accountId = a.accountId " +
            "JOIN a.roles r WHERE r.name = 'ROLE_ADMIN'")
    List<User> findUsersByAdminRole();

    @Query("SELECT a.username as username, r.name as roleName FROM Account a JOIN a.roles r ORDER BY a.username")
    List<UserWithRoleResponse> findUsersWithRole();
}
