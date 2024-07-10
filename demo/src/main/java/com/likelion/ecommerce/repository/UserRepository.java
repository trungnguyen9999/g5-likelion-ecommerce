package com.likelion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.User;

import java.util.Optional;

/**
 * Repository is an interface that provides access to data in a database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findFirstByAccountId(Integer accountId);

//    Optional<User> findByUsername(String username);

//    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
