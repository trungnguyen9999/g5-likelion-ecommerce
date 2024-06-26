package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.repository.CategoryRepo;
import com.likelion.ecommerce.response.PaginateResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
	@Autowired
	private  CategoryRepo repo;

    public List<Category> getAllCategory(){
        return repo.findAll();
    }
    
    public PaginateResponse paginateCategory(Pageable page){
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

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory= repo.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        return null;
    }

    public Category saveCategory (Category category){
        Category savedCategory = repo.save(category);
        return savedCategory;
    }

    public Category updateCategory (Category category) {
        Optional<Category> existingCategory = repo.findById(category.getCategoryId());
        Category updatedCategory = repo.save(category);
        return updatedCategory;
    }

    public void deleteCategoryById (Integer id) {
        repo.deleteById(id);
    }
}
