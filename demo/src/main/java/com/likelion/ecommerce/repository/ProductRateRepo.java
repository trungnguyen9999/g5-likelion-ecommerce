package com.likelion.ecommerce.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.ProductRate;

@Repository
public interface ProductRateRepo extends JpaRepository<ProductRate, Long>{
	
	List<ProductRate> findAllByProductId(Integer productId);
	
	Integer countAllByProductId(Integer productId);

	@Query(value =  "SELECT sum(case when score = 5 then 1 else 0 end) as s5, " +
			"	sum(case when score = 4 then 1 else 0 end) as s4, " +
			"	sum(case when score = 3 then 1 else 0 end) as s3, " +
			"	sum(case when score = 2 then 1 else 0 end) as s2, " +
			"	sum(case when score = 1 then 1 else 0 end) as s1  " +
			" FROM product_rate " +
			" WHERE product_id = :productId", nativeQuery = true)
	Map getStartGroupByProductId(Integer productId);
}
