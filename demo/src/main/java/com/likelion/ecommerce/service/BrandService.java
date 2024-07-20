package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.Brand;
import com.likelion.ecommerce.repository.BrandRepo;
import com.likelion.ecommerce.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandService {

	private final BrandRepo repo;

	private final ProductRepo productRepo;
	
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
		Brand temp = repo.findBrandByName(brand.getName());
		if (Objects.nonNull(temp)) {
			log.info("Brand exists!!!");
			return temp;
		}
		return repo.save(brand);
	}
	
	public void delete(Integer id) 
	{
		if(productRepo.countByBrandId(id) == 0) 
			repo.deleteById(id);
	}
}
