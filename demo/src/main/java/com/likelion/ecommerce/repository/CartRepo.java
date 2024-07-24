package com.likelion.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import com.likelion.ecommerce.response.SumQuantityCartResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	List<Cart> findAllByAccountIdOrderByCartIdDesc(Integer accountId);
	
	Cart findByAccountIdAndProductId(Integer accountId, Integer productId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.accountId = :accountId")
	void deleteCartByAccountId(Integer accountId);

	@Query("SELECT SUM(c.quantity) as sumQuantity FROM Cart c WHERE c.accountId = :accountId")
	Optional<SumQuantityCartResponse> getSumQuantityByAccountId (Integer accountId);

	@Query(value = " SELECT SUM(c.quantity * p.price) AS total_price " +
			" FROM carts c " +
			" INNER JOIN products p USING (product_id) " +
			" WHERE c.account_id = :accountId", nativeQuery = true)
	Integer getTotalPriceInCartByAccountId (Integer accountId);
}
