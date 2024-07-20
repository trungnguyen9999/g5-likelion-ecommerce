package com.likelion.ecommerce.service;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.dto.ProductRateDto;
import com.likelion.ecommerce.entities.ProductRate;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.ProductRateRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductRateService {

	private final ProductRateRepo repo;

	private final ModelMapper modelMapper;

	private final UserService userService;
	
	public List<ProductRateDto> findAllByProductId(Integer productId){
        return repo.findAllByProductId(productId).stream().map(i -> {
			ProductRateDto prDto = modelMapper.map(i, ProductRateDto.class);
			User u = userService.findFirstByAccountId(prDto.getAccountId());
			if(Objects.nonNull(u)) {
				prDto.setUserFirstName(u.getFirstName());
				prDto.setUserLastName(u.getLastName());
				prDto.setAvatar(u.getAvatar());
			}
			return prDto;
		}).collect(Collectors.toList());
	}
	
	public List<ProductRate> findBasicAllByProductId(Integer productId){
		return repo.findAllByProductId(productId);
	}
	
	public Float getScoreByProductId(Integer productId) {
		float score = 0f;
		List<ProductRate> listPR = repo.findAllByProductId(productId);
		if(Objects.nonNull(listPR) && !listPR.isEmpty()) {
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

	public ProductRate saveProductRate(ProductRate productRate) {
		return repo.save(productRate);
	}

	public LinkedHashMap getStartGroupByProductId(Integer productId) {
		Map data = repo.getStartGroupByProductId(productId);
		LinkedHashMap rs = new LinkedHashMap();
		rs.put(5, Integer.valueOf(data.get("s5").toString()));
		rs.put(4, Integer.valueOf(data.get("s4").toString()));
		rs.put(3, Integer.valueOf(data.get("s3").toString()));
		rs.put(2, Integer.valueOf(data.get("s2").toString()));
		rs.put(1, Integer.valueOf(data.get("s1").toString()));
		return rs;
	}
}
