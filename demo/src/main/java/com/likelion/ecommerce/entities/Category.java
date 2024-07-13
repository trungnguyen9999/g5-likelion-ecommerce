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
@Entity
@Data
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_category_id_seq")
    @SequenceGenerator(name = "categories_category_id_seq", sequenceName = "categories_category_id_seq", allocationSize = 1)
	private Integer categoryId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "deleted_at")
	private Date deletedAt;
}
