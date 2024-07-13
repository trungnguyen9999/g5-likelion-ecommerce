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
@Table(name = "orders_products")
public class OrderProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_products_id_seq")
    @SequenceGenerator(name = "orders_products_id_seq", sequenceName = "orders_products_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Double price;

}
