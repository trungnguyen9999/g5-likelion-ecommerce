package com.likelion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	List<Cart> findAllByAccountId(Integer accountId);
	
	Cart findByAccountIdAndProductId(Integer accountId, Integer productId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.accountId = :accountId")
	void deleteCartByAccountId(Integer accountId);
}
