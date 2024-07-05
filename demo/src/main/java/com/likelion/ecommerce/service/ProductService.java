package com.likelion.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.dto.ProductDetailDto;
import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.entities.WishList;
import com.likelion.ecommerce.repository.ProductRepo;
import com.likelion.ecommerce.request.PaginateProductRequest;
import com.likelion.ecommerce.request.PaginateRequest;
import com.likelion.ecommerce.response.ResponsePaginate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private ProductImagesService productImagesService;
	
	@Autowired
	private ProductRateService productRateService;
	
	@Autowired
	private ModelMapper modelMapper;

    public List<Product> getAllProduct(){
        return repo.findAll();
    }
    
    public ResponsePaginate paginateProduct(Pageable page, PaginateProductRequest request){
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
	    	
	    	List<ProductDetailDto> listProductDeTail = repo.findAll(page).stream().map(product -> {
	    		ProductDetailDto dto = modelMapper.map(product, ProductDetailDto.class);
	    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
	    	    dto.setCategoryDto(categoryDto);
	    	    
	    	    boolean isInWishList = Objects.nonNull(wishListService.findFirstByAccountIdAndProductId(request.getAccountId(), product.getProductId()));
	    	    dto.setInWishList(isInWishList);
	    	    
	    	    List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
	    	                                                    .stream()
	    	                                                    .map(i -> i.getImagePath())
	    	                                                    .collect(Collectors.toList());
	    	    dto.setImagesPath(listProductImagePath);
	    	    dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
	    	    dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
	    		return dto;
	    	}).collect(Collectors.toList());

	    	response.setItems(listProductDeTail);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return response;
    }

	public ResponsePaginate paginateProductGetByCategory(Integer categoryId, Pageable page, PaginateProductRequest request){
		ResponsePaginate response = new ResponsePaginate();
		try {
			float totalElement = repo.countByCategoryId(categoryId);
			int totalPage = 0;
			if(totalElement > 0) {
				totalPage = (int) Math.ceil(totalElement / page.getPageSize());
			}
			response.setCurentPage(page.getPageNumber() + 1);
			response.setPageSize(page.getPageSize());
			response.setTotalPages(totalPage);
			response.setTotalElements(Math.round(totalElement));

			List<ProductDetailDto> listProductDeTail = repo.findAllByCategoryId(categoryId, page).stream().map(product -> {
				ProductDetailDto dto = modelMapper.map(product, ProductDetailDto.class);
				CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
				dto.setCategoryDto(categoryDto);

				boolean isInWishList = Objects.nonNull(wishListService.findFirstByAccountIdAndProductId(request.getAccountId(), product.getProductId()));
				dto.setInWishList(isInWishList);

				List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
						.stream()
						.map(i -> i.getImagePath())
						.collect(Collectors.toList());
				dto.setImagesPath(listProductImagePath);
				dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
				dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
				return dto;
			}).collect(Collectors.toList());

			response.setItems(listProductDeTail);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}
    
    public ResponsePaginate paginateProductInWishList(Pageable page, PaginateProductRequest request) {
    	ResponsePaginate response = new ResponsePaginate();
    	try {
    		List<WishList> listWL = wishListService.findAllByAccountId(request.getAccountId());
	    	float totalElement = listWL.size();
	    	int totalPage = 0; 
	    	if(totalElement > 0) {
	    		totalPage = (int) Math.ceil(totalElement / page.getPageSize());
	    	}
	    	response.setCurentPage(page.getPageNumber() + 1);
	    	response.setPageSize(page.getPageSize());
	    	response.setTotalPages(totalPage);
	    	response.setTotalElements(Math.round(totalElement));
	    	
	    	int start = page.getPageNumber()*page.getPageSize();
	    	int end = page.getPageNumber()*page.getPageSize() + page.getPageSize() ;
	    	if(listWL.size() < page.getPageSize()) {
	    		end = listWL.size();
	    	}
	    	
	    	listWL = listWL.subList(start, end);
	    	
	    	List<ProductDetailDto> listProductDeTail = listWL.stream().map(i -> {
	    		Product product = repo.findById(i.getProductId()).orElse(null);
	    		ProductDetailDto dto =  modelMapper.map(product, ProductDetailDto.class);
	    		dto.setInWishList(true);
	    		dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
	    		dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
	    		return dto;
	    	}).collect(Collectors.toList());
	    	
	    	response.setItems(listProductDeTail);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return response;
	}

    public ProductDetailDto getProductById(Integer id){
        Optional<Product> optionalProduct= repo.findById(id);
        if(optionalProduct.isPresent()){
        	Optional<ProductDetailDto> productDetails = optionalProduct.map(product -> {
	    		ProductDetailDto dto = modelMapper.map(product, ProductDetailDto.class);
	    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
	    	    dto.setCategoryDto(categoryDto);
	    	    	    	    
	    	    List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
	    	                                                    .stream()
	    	                                                    .map(i -> i.getImagePath())
	    	                                                    .collect(Collectors.toList());
	    	    dto.setImagesPath(listProductImagePath);
	    	    dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
	    	    dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
	    		return dto;
	    	});
        	return productDetails.orElse(null);
        }
        return null;
    }

    public Product saveProduct (Product product){
    	product.setCreatedAt(new Date());
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

	public List<ProductDetailDto> getProductsNewArrival() {
		List<ProductDetailDto> listProductDeTail = repo.findNewArrival().stream().map(product -> {
    		ProductDetailDto dto = modelMapper.map(product, ProductDetailDto.class);
    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
    	    dto.setCategoryDto(categoryDto);
    	    
    	    List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
    	                                                    .stream()
    	                                                    .map(i -> i.getImagePath())
    	                                                    .collect(Collectors.toList());
    	    dto.setImagesPath(listProductImagePath);
    	    dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
    	    dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
    		return dto;
    	}).collect(Collectors.toList());
		return listProductDeTail;
	}

	public List<ProductDetailDto> getProductsBestSelling() {
		List<ProductDetailDto> results = repo.findBestSelling().stream().map(p -> {
			Product product = repo.findById(Integer.valueOf(p.get("product_id").toString())).orElse(null);
			ProductDetailDto dto = 		modelMapper.map(product, ProductDetailDto.class);
    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
    	    dto.setCategoryDto(categoryDto);
    	    
    	    List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
    	                                                    .stream()
    	                                                    .map(i -> i.getImagePath())
    	                                                    .collect(Collectors.toList());
    	    dto.setImagesPath(listProductImagePath);
    	    dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
    	    dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
			return dto;
		}).collect(Collectors.toList());
		return results;
	}

	
}
