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
@Table(name = "product_images")
public class ProductImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_images_id_seq")
    @SequenceGenerator(name = "product_images_id_seq", sequenceName = "product_images_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "description")
	private String description;

}
