package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.BannerImage;
import com.likelion.ecommerce.repository.BannerImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerImageService {

	private final BannerImageRepo repo;
	
	public List<BannerImage> findAllBannerImage(){
		return repo.findAllByIsDeleted(false);
	}
	
	public BannerImage saveBannerImage(BannerImage bannerImage) {
		return repo.save(bannerImage);
	}

	public BannerImage updateBannerImage(BannerImage bannerImage) {
        return repo.save(bannerImage);
	}
}
