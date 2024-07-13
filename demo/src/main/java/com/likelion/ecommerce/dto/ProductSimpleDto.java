package com.likelion.ecommerce.dto;

import lombok.Data;

@Data
public class ProductSimpleDto {
	
	private Integer productId;
	
	private String name;
	
	private Long price;
	
	private String imagePath;
	
	private Integer quantity;

}
