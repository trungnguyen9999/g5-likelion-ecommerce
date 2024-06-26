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
}
