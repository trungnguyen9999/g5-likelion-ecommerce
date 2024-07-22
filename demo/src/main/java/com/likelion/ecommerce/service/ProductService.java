package com.likelion.ecommerce.service;

import com.likelion.ecommerce.dto.CategoryDto;
import com.likelion.ecommerce.dto.ProductDetailDto;
import com.likelion.ecommerce.entities.Brand;
import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.entities.ProductImage;
import com.likelion.ecommerce.entities.WishList;
import com.likelion.ecommerce.repository.ProductRepo;
import com.likelion.ecommerce.request.PaginateProductRequest;
import com.likelion.ecommerce.response.ResponsePaginate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepo repo;

	private final CategoryService categoryService;

	private final WishListService wishListService;

	private final ProductImagesService productImagesService;

	private final ProductRateService productRateService;

	private final BrandService brandService;

	private final ModelMapper modelMapper;
    
    public ResponsePaginate paginateProduct(Pageable page, PaginateProductRequest request, 
    		int categoryId, String keyWord, Long fromPrice, Long toPrice, Integer sortBy, String sortType, List<Integer> brandIds, boolean getAll){
    	ResponsePaginate response = new ResponsePaginate();
    	try {
    		
    		int limit = page.getPageSize();
    		int offset = limit  * (page.getPageNumber() + 1) - limit;
    		
    		List<Product> listProduct = repo.filterProduct(keyWord, categoryId, fromPrice, toPrice, this.generateStrSortBy(sortBy, sortType), limit, offset, brandIds, getAll);

			float totalElement = 0;

			if(categoryId > 0 && !brandIds.isEmpty()) {
				totalElement = repo.countFilterProductHasCategoryIdAndBrandIds(keyWord, categoryId, brandIds, fromPrice, toPrice, getAll);
			} else if (categoryId > 0) {
				totalElement = repo.countFilterProductHasCategoryId(keyWord, categoryId, fromPrice, toPrice, getAll);
			} else if (!brandIds.isEmpty()) {
				totalElement = repo.countFilterProductHasBrandIds(keyWord, brandIds, fromPrice, toPrice, getAll);
			} else {
				totalElement = repo.countFilterProduct(keyWord, fromPrice, toPrice, getAll);
			}
	    	
	    	int totalPage = 0; 
	    	if(totalElement > 0) {
	    		totalPage = (int) Math.ceil(totalElement / page.getPageSize());
	    	}
	    	response.setCurentPage(page.getPageNumber() + 1);
	    	response.setPageSize(page.getPageSize());
	    	response.setTotalPages(totalPage);
	    	response.setTotalElements(Math.round(totalElement));
	    	
	    	List<ProductDetailDto> listProductDeTail = listProduct.stream().map(this::convertProduct2Dto).collect(Collectors.toList());

	    	response.setItems(listProductDeTail);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return response;
    }

	private String generateStrSortBy(Integer sortBy, String sortType) {
		//1: mới/cũ; 2: Giá; 3: Bán chạy
		if(sortBy == 1) {
			return " ORDER BY created_at " + sortType;
		}
		if(sortBy == 2) {
			return " ORDER BY price " + sortType;
		}
		if(sortBy == 3) {
			return " ORDER BY quantity_sold " + sortType;
		}
		return "";
	}

	public ResponsePaginate paginateProductGetByCategory(Integer categoryId, Pageable page, Integer productIdNotInclude){
		ResponsePaginate response = new ResponsePaginate();
		try {
			float totalElement = repo.countByCategoryIdAndDeletedAtIsNullAndProductIdNot(categoryId, productIdNotInclude);
			int totalPage = 0;
			if(totalElement > 0) {
				totalPage = (int) Math.ceil(totalElement / page.getPageSize());
			}
			response.setCurentPage(page.getPageNumber() + 1);
			response.setPageSize(page.getPageSize());
			response.setTotalPages(totalPage);
			response.setTotalElements(Math.round(totalElement));

			List<ProductDetailDto> listProductDeTail = repo.findAllByCategoryId(categoryId, page, productIdNotInclude).stream().map(this::convertProduct2Dto).collect(Collectors.toList());

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
				return this.convertProduct2Dto(product);
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
        	Optional<ProductDetailDto> productDetails = optionalProduct.map(this::convertProduct2Dto);
        	return productDetails.orElse(null);
        }
        return null;
    }

    public Product saveProduct (Product product){
    	product.setCreatedAt(new Date());
        return repo.save(product);
    }

    public Product updateProduct (Product product) {
        return repo.save(product);
    }

	public List<ProductDetailDto> getProductsNewArrival() {
        return repo.findNewArrival().stream().map(this::convertProduct2Dto).collect(Collectors.toList());
	}

	public List<ProductDetailDto> getProductsBestSelling() {
        return repo.findBestSelling().stream().map(p -> {
			Product product = repo.findById(Integer.valueOf(p.get("product_id").toString())).orElse(null);
			return this.convertProduct2Dto(product);
		}).collect(Collectors.toList());
	}

	public Integer countByBrandId(Integer brandId) {
		return repo.countByBrandId(brandId);
	}

	public Integer countByCategoryId(Integer categoryId) {
		return repo.countByCategoryId(categoryId);
	}

	private ProductDetailDto convertProduct2Dto(Product product){
		ProductDetailDto dto = 	modelMapper.map(product, ProductDetailDto.class);

		if(Objects.nonNull(product.getCategoryId())) {
			CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
			dto.setCategoryDto(categoryDto);
		}

		List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
				.stream()
				.map(ProductImage::getImagePath)
				.collect(Collectors.toList());
		dto.setImagesPath(listProductImagePath);
		dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
		dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
		if(Objects.nonNull(product.getBrandId())) {
			Brand br = brandService.findById(product.getBrandId());
			if (Objects.nonNull(br)) {
				dto.setBrand(br);
			}
		}
		return dto;
	}
}
