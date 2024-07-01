package com.likelion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.WishList;

public interface WishListRepo extends JpaRepository<WishList, Integer>{
	
	List<WishList> findAllByAccountIdOrderByWishlistId(Integer accountId);
	
	WishList findFirstByAccountIdAndProductIdOrderByWishlistId(Integer accountId, Integer productId);
	
	void deleteAllByAccountIdAndProductId(Integer accountId, Integer productId);
}
