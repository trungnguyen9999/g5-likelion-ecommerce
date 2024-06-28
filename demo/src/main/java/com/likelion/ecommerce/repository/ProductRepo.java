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

	@Query("SELECT p FROM Product p WHERE p.categoryId = ?1")
	public Page<Product> searchInCategory(Integer categoryId, Pageable pageable);
}
