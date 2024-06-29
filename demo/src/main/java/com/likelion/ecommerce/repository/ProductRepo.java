package com.likelion.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {


	@Query(value =  "SELECT * FROM products",
	countQuery = "SELECT count(product_id) FROM products ", nativeQuery = true)
	Page<Product> findAll(Pageable pageable);

	@Query(value =  "SELECT * FROM products where category_id = :categoryId",
			countQuery = "SELECT count(product_id) FROM products where category_id = :categoryId", nativeQuery = true)
	public Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);
	
	Integer countByCategoryId(Integer categoryId);
}
