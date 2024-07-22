package com.likelion.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import com.likelion.ecommerce.response.NumberProductsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.WishList;
import org.springframework.data.jpa.repository.Query;

public interface WishListRepo extends JpaRepository<WishList, Integer>{
	
	List<WishList> findAllByAccountIdOrderByWishlistId(Integer accountId);
	
	WishList findFirstByAccountIdAndProductIdOrderByWishlistId(Integer accountId, Integer productId);
	
	void deleteByAccountIdAndProductId(Integer accountId, Integer productId);

	@Query("SELECT COUNT(w.productId) as numberProducts FROM WishList w WHERE w.accountId = :accountId")
	Optional<NumberProductsResponse> getNumberProductsByAccountId (Integer accountId);
}
