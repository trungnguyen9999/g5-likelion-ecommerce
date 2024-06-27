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
@Data
@Entity
@Table(name = "wishlist")
public class WishList {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_wishlist_id_seq")
    @SequenceGenerator(name = "wishlist_wishlist_id_seq", sequenceName = "wishlist_wishlist_id_seq", allocationSize = 1)
	private Integer wishlistId;
	
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "product_id")
	private Integer productId;

	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
