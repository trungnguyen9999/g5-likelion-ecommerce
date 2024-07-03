package com.likelion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.ProductRate;

@Repository
public interface ProductRateRepo extends JpaRepository<ProductRate, Long>{
	
	List<ProductRate> findAllByProductId(Integer productId);
	
	Integer countAllByProductId(Integer productId);
}
