package com.likelion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.User;

/**
 * Repository is an interface that provides access to data in a database
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}