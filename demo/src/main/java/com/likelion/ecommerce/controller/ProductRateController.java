package com.likelion.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.entities.ProductRate;
import com.likelion.ecommerce.service.ProductRateService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/product-rate")
@RequiredArgsConstructor
@Validated
public class ProductRateController {

	@Autowired
	private ProductRateService productRateService;
	
	@GetMapping("/{productId}")
    public ResponseEntity<List<ProductRate>> findAllByProductId(@PathVariable Integer productId) {
        List<ProductRate> listProductRate = productRateService.findAllByProductId(productId);
        
        return ResponseEntity.ok()
        		.body(listProductRate);
    }
	
	@GetMapping("/score/{productId}")
    public ResponseEntity<?> getScoreByProductId(@PathVariable Integer productId) {        
        return ResponseEntity.ok()
        		.body(productRateService.getScoreByProductId(productId));
    }
}
