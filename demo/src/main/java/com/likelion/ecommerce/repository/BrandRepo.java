package com.likelion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.ecommerce.entities.Brand;

public interface BrandRepo extends JpaRepository<Brand, Integer>{

}
