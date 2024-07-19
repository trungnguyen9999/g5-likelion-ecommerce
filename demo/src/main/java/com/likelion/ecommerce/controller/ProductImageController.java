package com.likelion.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.likelion.ecommerce.entities.ProductImage;
import com.likelion.ecommerce.service.ProductImagesService;

import lombok.RequiredArgsConstructor;

@Slf4j
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

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductImageByPath(@RequestParam String path)
    {
            service.deleteProductImageByPath(path);
            return ResponseEntity.ok().body("Delete successful!");
    }
}
