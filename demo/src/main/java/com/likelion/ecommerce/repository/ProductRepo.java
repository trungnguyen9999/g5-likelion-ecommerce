package com.likelion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
