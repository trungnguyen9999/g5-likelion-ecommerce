package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.repository.ProductRepo;
import com.likelion.ecommerce.response.PaginateResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	@Autowired
	private  ProductRepo repo;

    public List<Product> getAllProduct(){
        return repo.findAll();
    }
    
    public PaginateResponse paginateProduct(Pageable page){
    	PaginateResponse response = new PaginateResponse();
    	try {
	    	float totalElement = repo.count();
	    	int totalPage = 0; 
	    	if(totalElement > 0) {
	    		totalPage = (int) Math.ceil(totalElement / page.getPageSize());
	    	}
	    	response.setCurentPage(page.getPageNumber() + 1);
	    	response.setPageSize(page.getPageSize());
	    	response.setTotalPages(totalPage);
	    	response.setTotalElements(Math.round(totalElement));
	    	
	    	response.setItems(repo.findAll(page).getContent());
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return response;
    }

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct= repo.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    public Product saveProduct (Product product){
        Product savedProduct = repo.save(product);
        return savedProduct;
    }

    public Product updateProduct (Product product) {
        Optional<Product> existingProduct = repo.findById(product.getProductId());
        Product updatedProduct = repo.save(product);
        return updatedProduct;
    }

    public void deleteProductById (Integer id) {
        repo.deleteById(id);
    }
}
