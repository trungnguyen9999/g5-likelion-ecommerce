package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.entities.ProductImage;
import com.likelion.ecommerce.repository.CategoryRepo;
import com.likelion.ecommerce.repository.ProductImagesRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImagesService {

	private final ProductImagesRepo repo;

    public List<ProductImage> getAllProductImage(){
        return repo.findAll();
    }
    
    public List<ProductImage> findAllByProductId(Integer productId){
        return repo.findAllByProductId(productId);
    }

    public ProductImage getProductImageById(Integer id){
        Optional<ProductImage> optionalProductImage= repo.findById(id);
        return optionalProductImage.orElse(null);
    }

    public ProductImage saveProductImage (ProductImage productImage){
        return repo.save(productImage);
    }

    public ProductImage updateProductImage (ProductImage productImage) {
        Optional<ProductImage> existingProductImage = repo.findById(productImage.getId());
        return repo.save(productImage);
    }

    public void deleteProductImageById (Integer id) {
        repo.deleteById(id);
    }

    @Transactional
    public void deleteProductImageByPath (String path) {
        log.info(path);
        repo.deleteByImagePath(path);
    }

}
