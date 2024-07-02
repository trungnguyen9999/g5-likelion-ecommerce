package com.likelion.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.BannerImage;
import com.likelion.ecommerce.repository.BannerImageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerImageService {
	@Autowired
	private BannerImageRepo repo;
	
	public List<BannerImage> findAllBannerImage(){
		return repo.findAllByIsDeleted(false);
	}
	
	public BannerImage saveBannerImage(BannerImage bannerImage) {
		return repo.save(bannerImage);
	}
}
