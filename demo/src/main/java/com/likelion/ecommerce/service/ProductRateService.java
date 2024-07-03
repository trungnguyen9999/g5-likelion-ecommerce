package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.ProductRate;
import com.likelion.ecommerce.repository.ProductRateRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductRateService {

	@Autowired
	private ProductRateRepo repo;
	
	public List<ProductRate> findAllByProductId(Integer productId){
		return repo.findAllByProductId(productId);
	}
	
	public Float getScoreByProductId(Integer productId) {
		Float score = 0f;
		List<ProductRate> listPR = repo.findAllByProductId(productId);
		if(Objects.nonNull(listPR) && listPR.size() > 0) {
			float totalScore = 0;
			for(ProductRate pr : listPR) {
				totalScore += pr.getScore();
			}
			score = totalScore / (float) listPR.size();
		}
		return Math.round((score*10)) / (float) 10 ;
	}
	
	public Integer countAllByProductId(Integer productId) {
		return repo.countAllByProductId(productId);
	}
	
}
