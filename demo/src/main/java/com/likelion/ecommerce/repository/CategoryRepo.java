package com.likelion.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
