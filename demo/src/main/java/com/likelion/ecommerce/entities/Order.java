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
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_order_id_seq")
    @SequenceGenerator(name = "orders_order_id_seq", sequenceName = "orders_order_id_seq", allocationSize = 1)
	private Integer orderId;
	
	@Column(name = "account_id")
	private String accountId;
	
	@Column(name = "user_id")
	private String usertId;
	
	@Column(name = "total_price")
	private String totalPrice;
	
	@Column(name = "order_time")
	private String orderTime;
	
	@Column(name = "payment_type")
	private String paymentType;
	
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUsertId() {
		return usertId;
	}

	public void setUsertId(String usertId) {
		this.usertId = usertId;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}	
	
}
