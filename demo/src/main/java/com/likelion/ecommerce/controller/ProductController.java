package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.request.PaginateProductRequest;
import com.likelion.ecommerce.response.ResponsePaginate;
import com.likelion.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

	@GetMapping("/public/paginate")
    public ResponseEntity<ResponsePaginate> getAllProduct(
    		@RequestParam(defaultValue = "") String keyWord,
    		@RequestParam(required = true) Integer page,
    		@RequestParam(defaultValue = "10") Integer pageSize,
    		@RequestParam(defaultValue = "-1") Integer accountId,
    		@RequestParam(defaultValue = "-1") Integer categoryId,
    		@RequestParam(defaultValue = "0") Long fromPrice,
    		@RequestParam(defaultValue = "1000000") Long toPrice,
    		@RequestParam(defaultValue = "") List<Integer> brandIds,
            @RequestParam(defaultValue = "false") boolean getAll,
    		@RequestParam(defaultValue = "1") Integer sortBy,//1: mới/cũ; 2: Giá; 3: Bán chạy
    		@RequestParam(defaultValue = "ASC") String sortType) //ASC: tăng; DESC: Giảm
	{
    	Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("created_at").descending());
    	PaginateProductRequest request = new PaginateProductRequest(accountId, page, pageSize);    	
        return ResponseEntity.ok()
        		.body(productService.paginateProduct(pageable, request, categoryId, keyWord, fromPrice, toPrice, sortBy, sortType, brandIds, getAll));
    }
    
	@GetMapping("/public/{categoryid}/paginate")
    public ResponseEntity<ResponsePaginate> getAllProduct(
            @PathVariable Integer categoryid,
            @RequestParam(name = "page", required = true) Integer page,
    		@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
    		@RequestParam(name = "accountId", defaultValue = "-1") Integer accountId)
	{
    	Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("created_at").descending());
        return ResponseEntity.ok()
        		.body(productService.paginateProductGetByCategory(categoryid, pageable, 0));
    }

    @GetMapping("/public/{categoryid}/exclude")
    public ResponseEntity<ResponsePaginate> getAllProductByCategoryIdExclude(
            @PathVariable Integer categoryid,
            @RequestParam(name = "pageSize",  defaultValue = "10") Integer pageSize,
            @RequestParam(name = "productIdNotInclude", defaultValue = "0") Integer productIdNotInclude)
    {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by("created_at").descending());
        return ResponseEntity.ok()
                .body(productService.paginateProductGetByCategory(categoryid, pageable, productIdNotInclude));
    }
    
    @GetMapping("/paginate/wishlist")
    public ResponseEntity<ResponsePaginate> getAllProductInWishList(@RequestBody PaginateProductRequest request) 
    {
    	Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize(), Sort.by("created_at").descending());
        return ResponseEntity.ok()
        		.body(productService.paginateProductInWishList(pageable, request));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) 
    {
        return ResponseEntity.ok()
        		.body(productService.getProductById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) 
    {
    	product.setCreatedAt(new Date());
        return ResponseEntity.ok()
        		.body(productService.saveProduct(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) 
    {
        return ResponseEntity.ok()
        		.body(productService.updateProduct(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id) 
    {
//    	productService.deleteProductById(id);
        return ResponseEntity.ok()
        		.body("Deleted product successfully");
    }
   
    @GetMapping("/public/new-arrival")
    public ResponseEntity<?> getProductsNewArrival()
    {
        return ResponseEntity.ok()
        		.body(productService.getProductsNewArrival());
    }

    @GetMapping("/public/best-selling")
    public ResponseEntity<?> getProductsBestSelling()
    {
        return ResponseEntity.ok()
        		.body(productService.getProductsBestSelling());
    }

}
