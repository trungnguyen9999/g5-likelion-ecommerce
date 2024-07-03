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
@Table(name = "product_rate")
public class ProductRate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_rate_id_seq")
    @SequenceGenerator(name = "product_rate_id_seq", sequenceName = "product_rate_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "rate_time")
	private String rateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the rateTime
	 */
	public String getRateTime() {
		return rateTime;
	}

	/**
	 * @param rateTime the rateTime to set
	 */
	public void setRateTime(String rateTime) {
		this.rateTime = rateTime;
	}
	
	
}
