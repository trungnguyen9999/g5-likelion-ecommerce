package com.likelion.ecommerce.dto;

import com.likelion.ecommerce.entities.Category;
import lombok.Data;

@Data
public class ProductSimpleDto {
	
	private Integer productId;
	
	private String name;
	
	private Long price;
	
	private String imagePath;
	
	private Integer quantity;

	private CategoryDto categoryDto;

	private Integer subTotal;

}
