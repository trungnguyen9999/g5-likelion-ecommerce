package com.likelion.ecommerce.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_product_id_seq")
    @SequenceGenerator(name = "products_product_id_seq", sequenceName = "products_product_id_seq", allocationSize = 1)
	private Integer productId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "brand_id")
	private Integer brandId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "quantity_sold")
	private Integer quantitySold;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "deleted_at")
	private Date deletedAt;

}
