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

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_order_id_seq")
    @SequenceGenerator(name = "orders_order_id_seq", sequenceName = "orders_order_id_seq", allocationSize = 1)
	private Integer orderId;
	
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "user_id")
	private Integer usertId;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "order_time")
	private Date orderTime;
	
	@Column(name = "payment_type")
	private Integer paymentType;
	
	@Column(name = "address_line1")
	private String addressLine1;		
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "apartment")
	private String apartment;		
	
	@Column(name = "suburb")
	private String suburb;
	
	@Column(name = "city")
	private String city;		
	
	@Column(name = "region")
	private String region;

	@Column(name = "status")
	private Integer status;

	@Column(name = "comment")
	private String comment;

	@Column(name = "coupon_id")
	private Integer couponId;
}
