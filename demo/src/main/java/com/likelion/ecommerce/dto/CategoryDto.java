package com.likelion.ecommerce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {
	private Integer categoryId;
	
	private String name;
	
	private String imagePath;
	
	private Date deletedAt;

	private Integer quantityProduct;

}
