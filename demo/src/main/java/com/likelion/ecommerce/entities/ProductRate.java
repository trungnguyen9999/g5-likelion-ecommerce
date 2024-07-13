package com.likelion.ecommerce.entities;

import java.util.Date;

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
	private Date rateTime;

}
