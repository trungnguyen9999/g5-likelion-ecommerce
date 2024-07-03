package com.likelion.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.repository.CategoryRepo;
import com.likelion.ecommerce.repository.ProductRepo;
import com.likelion.ecommerce.response.ResponsePaginate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	@Autowired
	private  CategoryRepo repo;
	
	@Autowired
	private  ProductRepo repoProduct;
	
	@Autowired
	private ModelMapper modelMapper;

    public List<Category> getAllCategory(){
        return repo.findAll();
    }
    
    public ResponsePaginate paginateCategory(Pageable page){
    	ResponsePaginate response = new ResponsePaginate();
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
        Category updatedCategory = repo.save(category);
        return updatedCategory;
    }

    public void deleteCategoryById (Integer id) {
        repo.deleteById(id);
    }

    public List<CategoryDto> getCategoryList() {
    	List<CategoryDto> listcategoryDto = repo.findAll().stream().map(item -> {
    		CategoryDto categoryDto = modelMapper.map(item, CategoryDto.class);
    		categoryDto.setQuantityProduct(repoProduct.countByCategoryId(item.getCategoryId()));
    		return categoryDto;
    	}).collect(Collectors.toList());
        return listcategoryDto;
    }
}
