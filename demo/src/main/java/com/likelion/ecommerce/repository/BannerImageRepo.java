package com.likelion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.BannerImage;

@Repository
public interface BannerImageRepo extends JpaRepository<BannerImage, Integer>{

	List<BannerImage> findAllByIsDeleted(Boolean isDeleted);
}
