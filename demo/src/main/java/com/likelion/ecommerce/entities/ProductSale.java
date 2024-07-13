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
@Table(name = "product_sale")
public class ProductSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sale_id_seq")
    @SequenceGenerator(name = "product_sale_id_seq", sequenceName = "product_sale_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "sale_percent")
	private String salePercent;

}
