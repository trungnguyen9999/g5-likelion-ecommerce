package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository is an interface that provides access to data in a database
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("select a from Account a join User u on u.accountId = a.accountId where u.email = :email")
    Optional<Account> findByEmail(String email);

}
