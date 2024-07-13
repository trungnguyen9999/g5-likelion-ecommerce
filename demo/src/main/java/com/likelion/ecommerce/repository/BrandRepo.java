package com.likelion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer>{
    Brand findBrandByName(String name);
}
