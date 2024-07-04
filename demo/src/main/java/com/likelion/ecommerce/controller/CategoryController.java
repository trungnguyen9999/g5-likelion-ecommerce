package com.likelion.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.request.PaginateRequest;
import com.likelion.ecommerce.response.ResponsePaginate;
import com.likelion.ecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {
	
	/**
	 * author: ntnguyen
	 */
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/paginate")
    public ResponseEntity<ResponsePaginate> paginateCategory(@RequestBody PaginateRequest request){
    	Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize(), Sort.by("categoryId").ascending());
        return ResponseEntity.ok()
        		.body(categoryService.paginateCategory(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCatgoryById(@PathVariable Integer id)
    {
    	Category cate = categoryService.getCategoryById(id);
        return ResponseEntity.ok()
        		.body(cate);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok()
        		.body(categoryService.saveCategory(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok()
        		.body(categoryService.updateCategory(category));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Integer id)
    {
    	categoryService.deleteCategoryById(id);
        return ResponseEntity.ok()
        		.body("Deleted category successfully");
    }

    @GetMapping("/public/all")
    public ResponseEntity<List<CategoryDto>> getCategoryList() {
        List<CategoryDto> listCategories = categoryService.getCategoryList();
        
        return ResponseEntity.ok()
        		.body(listCategories);
    }
}
