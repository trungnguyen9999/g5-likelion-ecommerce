package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Optional;

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
	@Autowired
	private  ProductImagesRepo repo;

    public List<ProductImage> getAllProductImage(){
        return repo.findAll();
    }
    
    public List<ProductImage> findAllByProductId(Integer productId){
        return repo.findAllByProductId(productId);
    }

    public ProductImage getProductImageById(Integer id){
        Optional<ProductImage> optionalProductImage= repo.findById(id);
        if(optionalProductImage.isPresent()){
            return optionalProductImage.get();
        }
        return null;
    }

    public ProductImage saveProductImage (ProductImage productImage){
        ProductImage savedProductImage = repo.save(productImage);
        return savedProductImage;
    }

    public ProductImage updateProductImage (ProductImage productImage) {
        Optional<ProductImage> existingProductImage = repo.findById(productImage.getId());
        ProductImage updatedProductImage = repo.save(productImage);
        return updatedProductImage;
    }

    public void deleteUserById (Integer id) {
        repo.deleteById(id);
    }
}
