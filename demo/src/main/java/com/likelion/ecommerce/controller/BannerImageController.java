package com.likelion.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.entities.BannerImage;
import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.service.BannerImageService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/banner-images")
@RequiredArgsConstructor
@Validated
public class BannerImageController {
	
	@Autowired
	private BannerImageService bannerImageService;
	
	@GetMapping("/public/all")
    public ResponseEntity<List<BannerImage>> getBannerImageList() {
        List<BannerImage> listBannerImages = bannerImageService.findAllBannerImage();
        
        return ResponseEntity.ok()
        		.body(listBannerImages);
    }

	@PostMapping("/create")
    public ResponseEntity<BannerImage> saveBannerImage(@RequestBody BannerImage bannerImage)
    {
        return ResponseEntity.ok()
        		.body(bannerImageService.saveBannerImage(bannerImage));
    }
	@PutMapping("/update")
    public ResponseEntity<BannerImage> updateBannerImage(@RequestBody BannerImage bannerImage)
    {
        return ResponseEntity.ok()
        		.body(bannerImageService.updateBannerImage(bannerImage));
    }
}
