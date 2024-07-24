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

	@Column(name = "country")
	private String country;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "region")
	private String region;

	@OneToOne(targetEntity = OrderStatus.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "status_id")
	private OrderStatus status;

	@Column(name = "description")
	private String description;

	@Column(name = "coupon_id")
	private Integer couponId;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderProduct> orderProducts;
}
