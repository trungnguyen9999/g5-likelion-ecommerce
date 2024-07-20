package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o ORDER BY o.orderId DESC")
    Optional<Order> findTopByOrderByIdDesc();
}
