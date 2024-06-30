package com.likelion.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.request.PaginateRequest;
import com.likelion.ecommerce.response.PaginateResponse;
import com.likelion.ecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin(origins = "https://g5-likelion-ecommerce.onrender.com", allowedHeaders = "*")
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

	@CrossOrigin
    @GetMapping("/paginate")
    public ResponseEntity<PaginateResponse> paginateCategory(@RequestBody PaginateRequest request){
    	Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize(), Sort.by("categoryId").ascending());
        return ResponseEntity.ok().body(categoryService.paginateCategory(pageable));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCatgoryById(@PathVariable Integer id)
    {
    	Category cate = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(cate);
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok().body(categoryService.saveCategory(category));
    }

    @CrossOrigin
    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Integer id)
    {
    	categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().body("Deleted category successfully");
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getCategoryList() {
        List<CategoryDto> listCategories = categoryService.getCategoryList();
        return ResponseEntity.ok().body(listCategories);
    }
}
