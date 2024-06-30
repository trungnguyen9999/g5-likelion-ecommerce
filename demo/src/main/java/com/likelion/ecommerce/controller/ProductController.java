package com.likelion.ecommerce.controller;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.request.PaginateProductRequest;
import com.likelion.ecommerce.response.PaginateResponse;
import com.likelion.ecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin(origins = "https://g5-likelion-ecommerce.onrender.com", allowedHeaders = "*")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {
	
	/**
	 * author: ntnguyen
	 */
	
	@Autowired
	private ProductService productService;

	@CrossOrigin
	@GetMapping("/paginate")
    public ResponseEntity<PaginateResponse> getAllProduct(
    		@RequestParam(name = "page", required = true) Integer page,
    		@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    		@RequestParam(name = "accountId", required = false, defaultValue = "-1") Integer accountId){
    	Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("created_at").descending());
    	PaginateProductRequest request = new PaginateProductRequest(accountId, page, pageSize);    	
        return ResponseEntity.ok().body(productService.paginateProduct(pageable, request));
    }
    
	@CrossOrigin
    @GetMapping("/{categoryid}/paginate")
    public ResponseEntity<PaginateResponse> getAllProduct(
            @PathVariable Integer categoryid,
            @RequestParam(name = "page", required = true) Integer page,
    		@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    		@RequestParam(name = "accountId", required = false, defaultValue = "-1") Integer accountId){
    	Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("created_at").descending());
    	PaginateProductRequest request = new PaginateProductRequest(accountId, page, pageSize);
        return ResponseEntity.ok().body(productService.paginateProductGetByCategory(categoryid, pageable, request));
    }
    
    @CrossOrigin
    @GetMapping("/paginate/wishlist")
    public ResponseEntity<PaginateResponse> getAllProductInWishList(@RequestBody PaginateProductRequest request){
    	Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize(), Sort.by("created_at").descending());
        return ResponseEntity.ok().body(productService.paginateProductInWishList(pageable, request));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        return ResponseEntity.ok().body(productService.saveProduct(product));
    }

    @CrossOrigin
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
    {
        return ResponseEntity.ok().body(productService.updateProduct(product));
    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id)
    {
    	productService.deleteProductById(id);
        return ResponseEntity.ok().body("Deleted product successfully");
    }

}
