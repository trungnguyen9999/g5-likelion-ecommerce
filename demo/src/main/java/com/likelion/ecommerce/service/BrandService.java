package com.likelion.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.Brand;
import com.likelion.ecommerce.repository.BrandRepo;
import com.likelion.ecommerce.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
	
	@Autowired
	private BrandRepo repo;
	
	@Autowired
	private ProductRepo productRepo;
	
	public Brand findById(Integer id) 
	{
		return repo.findById(id).orElse(null);
	}
	
	public List<Brand> findAll()
	{
		return repo.findAll();
	}
	
	public Brand save(Brand brand) 
	{
		return repo.save(brand);
	}
	
	public void delete(Integer id) 
	{
		if(productRepo.countByBrandId(id) == 0) 
			repo.deleteById(id);
	}
}
