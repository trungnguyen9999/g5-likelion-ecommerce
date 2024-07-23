package com.likelion.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
	private Integer cartId;
	
	private Integer accountId;
	
	private ProductSimpleDto product;

	private Integer totalItems;

	private Integer totalPrice;
}
