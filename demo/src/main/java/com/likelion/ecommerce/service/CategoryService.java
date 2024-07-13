package com.likelion.ecommerce.service;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.entities.Category;
import com.likelion.ecommerce.repository.CategoryRepo;
import com.likelion.ecommerce.repository.ProductRepo;
import com.likelion.ecommerce.response.ResponsePaginate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

	private final CategoryRepo repo;

	private final ProductRepo repoProduct;

	private final ModelMapper modelMapper;

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
        return optionalCategory.orElse(null);
    }

    public Category saveCategory (Category category){
        return repo.save(category);
    }

    public Category updateCategory (Category category) {
        return repo.save(category);
    }

    public void deleteCategoryById (Integer id) {
        repo.deleteById(id);
    }

    public List<CategoryDto> getCategoryList(String keyWord) {
    	List<CategoryDto> listcategoryDto = repo.findByNameContainingIgnoreCase(keyWord).stream().map(item -> {
    		CategoryDto categoryDto = modelMapper.map(item, CategoryDto.class);
    		categoryDto.setQuantityProduct(repoProduct.countByCategoryIdAndDeletedAtIsNull(item.getCategoryId()));
    		return categoryDto;
    	}).collect(Collectors.toList());
        return listcategoryDto;
    }
}
