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

	@Query(value =  "SELECT count(1) FROM products p where name ilike %:name% "
			+ " and price between :fromPrice and :toPrice and (p.deleted_at ISNULL OR :getAll)", nativeQuery = true)
	Integer countFilterProduct(String name, Long fromPrice, Long toPrice, boolean getAll);
	
	@Query(value =  "SELECT count(1) FROM products p where name ilike %:name% and category_id = :categoryId "
			+ " and price between :fromPrice and :toPrice and (p.deleted_at ISNULL OR :getAll)", nativeQuery = true)
	Integer countFilterProductHasCategoryId(String name, Integer categoryId, Long fromPrice, Long toPrice, boolean getAll);

	@Query(value =  "SELECT count(1) FROM products p where name ilike %:name% and brand_id in :brandIds "
			+ " and price between :fromPrice and :toPrice and (p.deleted_at ISNULL OR :getAll)", nativeQuery = true)
	Integer countFilterProductHasBrandIds(String name, List<Integer> brandIds, Long fromPrice, Long toPrice, boolean getAll);

	@Query(value =  "SELECT count(1) FROM products p where name ilike %:name% and category_id = :categoryId and brand_id in :brandIds "
			+ " and price between :fromPrice and :toPrice and (p.deleted_at ISNULL OR :getAll)", nativeQuery = true)
	Integer countFilterProductHasCategoryIdAndBrandIds(String name, Integer categoryId, List<Integer> brandIds, Long fromPrice, Long toPrice, boolean getAll);

	@Query(value =  "SELECT * FROM products p where category_id = :categoryId and p.deleted_at ISNULL and product_id <> :productId",
			countQuery = "SELECT count(product_id) FROM products p where category_id = :categoryId and p.deleted_at ISNULL and product_id <> :productId", nativeQuery = true)
	Page<Product> findAllByCategoryIdAndProductId(Integer categoryId, Pageable pageable, Integer productId);
	
	Integer countByCategoryIdAndDeletedAtIsNullAndProductIdNot(Integer categoryId, Integer ProductId);
	
	@Query(value =  "SELECT * FROM products p where p.deleted_at ISNULL ORDER BY created_at DESC LIMIT 8 ",  nativeQuery = true)
	List<Product> findNewArrival();

	@Query(value =  " SELECT product_id, quantity_sold AS total "
			+ " FROM products p "
			+ " WHERE p.deleted_at ISNULL "
			+ " ORDER by quantity_sold DESC, p.created_at ASC "
			+ " LIMIT 4",  nativeQuery = true)
	List<Map> findBestSelling();

	Integer countByBrandId(Integer brandId);

	Integer countByCategoryId(Integer categoryId);

}
