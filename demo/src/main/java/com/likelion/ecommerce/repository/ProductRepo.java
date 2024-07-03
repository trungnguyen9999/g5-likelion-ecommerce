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
public interface ProductRepo extends JpaRepository<Product, Integer> {


	@Query(value =  "SELECT * FROM products ",
	countQuery = "SELECT count(product_id) FROM products ", nativeQuery = true)
	Page<Product> findAll(Pageable pageable);

	@Query(value =  "SELECT * FROM products p where category_id = :categoryId",
			countQuery = "SELECT count(product_id) FROM products where category_id = :categoryId", nativeQuery = true)
	public Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);
	
	Integer countByCategoryId(Integer categoryId);
	
	@Query(value =  "SELECT * FROM products p ORDER BY created_at DESC LIMIT 8 ",  nativeQuery = true)
	List<Product> findNewArrival();
	
	@Query(value =  " SELECT product_id, SUM(op.quantity) AS total "
			+ " FROM products p "
			+ " LEFT JOIN orders_products op USING (product_id) "
			+ " LEFT JOIN orders o USING (order_id) "
			+ " WHERE p.deleted_at ISNULL  "
			+ " GROUP BY product_id "
			+ " ORDER by total DESC, p.created_at DESC "
			+ " LIMIT 4",  nativeQuery = true)
	List<Map> findBestSelling();
}
