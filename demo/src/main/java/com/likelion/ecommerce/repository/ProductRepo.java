package com.likelion.ecommerce.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>, ProductRepositoryCustom {


	@Query(value =  "SELECT * FROM products p where name ilike %:name% and price between :fromPrice and :toPrice and p.deleted_at ISNULL",
	countQuery = "SELECT count(product_id) FROM products p where name ilike %:name% and price between :fromPrice and :toPrice and p.deleted_at ISNULL", nativeQuery = true)
	Page<Product> findByNameContainingIgnoreCase(Pageable pageable, String name, Long fromPrice, Long toPrice);
	
	@Query(value =  "SELECT count(1) FROM products p where name ilike %:name% "
			+ " and price between :fromPrice and :toPrice and p.deleted_at ISNULL", nativeQuery = true)
	Integer countFilterProduct(String name, Long fromPrice, Long toPrice);
	
	@Query(value =  "SELECT count(1) FROM products p where name ilike %:name% and category_id = :categoryId "
			+ " and price between :fromPrice and :toPrice and p.deleted_at ISNULL", nativeQuery = true)
	Integer countFilterProductHasCategoryId(String name, Integer categoryId, Long fromPrice, Long toPrice);
	
	@Query(value =  "SELECT * FROM products p where category_id = :categoryId and p.deleted_at ISNULL",
			countQuery = "SELECT count(product_id) FROM products where category_id = :categoryId and p.deleted_at ISNULL", nativeQuery = true)
	public Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);
	
	Integer countByCategoryIdAndDeletedAtIsNull(Integer categoryId);
	
	@Query(value =  "SELECT * FROM products p where p.deleted_at ISNULL ORDER BY created_at DESC LIMIT 8 ",  nativeQuery = true)
	List<Product> findNewArrival();
	
	@Query(value =  " SELECT product_id, quantity_sold AS total "
			+ " FROM products p "
			+ " WHERE p.deleted_at ISNULL "
			+ " ORDER by quantity_sold DESC, p.created_at DESC "
			+ " LIMIT 4",  nativeQuery = true)
	List<Map> findBestSelling();
}
