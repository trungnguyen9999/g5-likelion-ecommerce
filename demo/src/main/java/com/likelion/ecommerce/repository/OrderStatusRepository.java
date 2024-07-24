package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    Optional<OrderStatus> findById(int orderId);
}
