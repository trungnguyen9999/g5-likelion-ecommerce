package com.likelion.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.entities.ProductImage;
import com.likelion.ecommerce.service.ProductImagesService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/product-image")
@RequiredArgsConstructor
@Validated
public class ProductImageController {

	private final ProductImagesService service;
	
	@PostMapping("/create")
    public ResponseEntity<ProductImage> saveProductImage(@RequestBody ProductImage pi) 
    {
        return ResponseEntity.ok()
        		.body(service.saveProductImage(pi));
    }
}
