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
@Table(name = "banner_images")
public class BannerImage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banner_images_id_seq")
    @SequenceGenerator(name = "banner_images_id_seq", sequenceName = "banner_images_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "alt")
	private String alt = "";

}
