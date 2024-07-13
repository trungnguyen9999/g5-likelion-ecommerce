package com.likelion.ecommerce.dto;

import java.util.Date;
import java.util.List;

import com.likelion.ecommerce.entities.Brand;

import lombok.Data;

@Data
public class ProductDetailDto {
	
	private Integer productId;
	
	private String name;
	
	private Integer quantity;
	
	private String description;
	
	private Long price;
	
	private float ratingScore = 0;
	
	private Integer rateTotal = 0;
	
	private CategoryDto categoryDto;
	
	private Brand brand;
	
	private Date createdAt;
	
	private Date deletedAt;
	
	private boolean inWishList;
	
	private String imagePath;
	
	private List<String> imagesPath;

}
