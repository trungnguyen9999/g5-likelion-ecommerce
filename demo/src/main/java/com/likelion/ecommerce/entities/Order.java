package com.likelion.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private ECurrency currency;
	
	@Column(name = "order_time")
	private Date orderTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type")
	private EPaymentType paymentType;

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

	@Column(name = "description")
	private String description;

	@Column(name = "coupon_id")
	private Integer couponId;

	@OneToMany(mappedBy = "order")
	private List<OrderProduct> orderProducts;
}
