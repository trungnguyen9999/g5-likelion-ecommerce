package com.likelion.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_cart_id_seq")
    @SequenceGenerator(name = "carts_cart_id_seq", sequenceName = "carts_cart_id_seq", allocationSize = 1)
	private Integer categoryId;
	
	@Column(name = "account_id")
	private String accountId;
	
	@Column(name = "product_id")
	private String productId;	
	
	@Column(name = "quantity")
	private String quantity;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
