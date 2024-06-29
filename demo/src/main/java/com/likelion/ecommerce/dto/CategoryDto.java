package com.likelion.ecommerce.dto;

import java.util.Date;


public class CategoryDto {
	private Integer categoryId;
	
	private String name;
	
	private String imagePath;
	
	private Date deletedAt;

	private Integer quantityProduct;
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	/**
	 * @return the quantityProduct
	 */
	public Integer getQuantityProduct() {
		return quantityProduct;
	}

	/**
	 * @param quantityProduct the quantityProduct to set
	 */
	public void setQuantityProduct(Integer quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	
	
}
