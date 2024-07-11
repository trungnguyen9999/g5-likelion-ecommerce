package com.likelion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	List<Cart> findAllByAccountId(Integer accountId);
	
	Cart findByAccountIdAndProductId(Integer accountId, Integer productId);
}
