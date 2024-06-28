package com.likelion.ecommerce.dto;

import java.util.Date;
import java.util.List;

public class ProductDetailDto {
	
	private Integer productId;
	
	private String name;
	
	private Integer quantity;
	
	private String description;
	
	private Long price;
	
	private CategoryDto categoryDto;
	
	private Date createdAt;
	
	private Date deletedAt;
	
	private boolean inWishList;
	
	private List<String> imagesPath;

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return the categoryDto
	 */
	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	/**
	 * @param categoryDto the categoryDto to set
	 */
	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the deletedAt
	 */
	public Date getDeletedAt() {
		return deletedAt;
	}

	/**
	 * @param deletedAt the deletedAt to set
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	/**
	 * @return the inWishList
	 */
	public boolean isInWishList() {
		return inWishList;
	}

	/**
	 * @param inWishList the inWishList to set
	 */
	public void setInWishList(boolean inWishList) {
		this.inWishList = inWishList;
	}

	/**
	 * @return the imagesPath
	 */
	public List<String> getImagesPath() {
		return imagesPath;
	}

	/**
	 * @param imagesPath the imagesPath to set
	 */
	public void setImagesPath(List<String> imagesPath) {
		this.imagesPath = imagesPath;
	}
	
	
}
