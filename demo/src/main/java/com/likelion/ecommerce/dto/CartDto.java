package com.likelion.ecommerce.dto;

import java.util.List;

public class CartDto {
	private Integer cartId;
	
	private Integer accountId;
	
	ProductSimpleDto product;

	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the cartId
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the product
	 */
	public ProductSimpleDto getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductSimpleDto product) {
		this.product = product;
	}

	
}
