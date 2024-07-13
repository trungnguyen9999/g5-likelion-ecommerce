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

    public List<Product> getAllProduct(){
		return repo.findAll();
    }
    
    public ResponsePaginate paginateProduct(Pageable page, PaginateProductRequest request, 
    		int categoryId, String keyWord, Long fromPrice, Long toPrice, Integer sortBy, String sortType, List<Integer> brandIds){
    	ResponsePaginate response = new ResponsePaginate();
    	try {
    		
    		int limit = page.getPageSize();
    		int offset = limit  * (page.getPageNumber() + 1) - limit;
    		
    		List<Product> listProduct = repo.filterProduct(keyWord, categoryId, fromPrice, toPrice, this.generateStrSortBy(sortBy, sortType), limit, offset, brandIds);

			float totalElement = 0;
			if(listProduct.size() < page.getPageSize()) {
				totalElement = listProduct.size();
			} else {
				if(categoryId > 0 && !brandIds.isEmpty()) {
					totalElement = repo.countFilterProductHasCategoryIdAndBrandIds(keyWord, categoryId, brandIds, fromPrice, toPrice);
				} else if (categoryId > 0) {
					repo.countFilterProductHasCategoryId(keyWord, categoryId, fromPrice, toPrice);
				} else if (!brandIds.isEmpty()) {
					repo.countFilterProductHasBrandIds(keyWord, brandIds, fromPrice, toPrice);
				} else {
					repo.countFilterProduct(keyWord, fromPrice, toPrice);
				}
			}
	    	
	    	int totalPage = 0; 
	    	if(totalElement > 0) {
	    		totalPage = (int) Math.ceil(totalElement / page.getPageSize());
	    	}
	    	response.setCurentPage(page.getPageNumber() + 1);
	    	response.setPageSize(page.getPageSize());
	    	response.setTotalPages(totalPage);
	    	response.setTotalElements(Math.round(totalElement));
	    	
	    	List<ProductDetailDto> listProductDeTail = listProduct.stream().map(product -> {
	    		ProductDetailDto dto = modelMapper.map(product, ProductDetailDto.class);
	    		
	    		if(Objects.nonNull(product.getCategoryId())) {
		    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
		    	    dto.setCategoryDto(categoryDto);
	    		}
	    	    
	    		boolean isInWishList = Objects.nonNull(wishListService.findFirstByAccountIdAndProductId(request.getAccountId(), product.getProductId()));
	    	    dto.setInWishList(isInWishList);
	    	    
	    	    List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
	    	                                                    .stream()
	    	                                                    .map(i -> i.getImagePath())
	    	                                                    .collect(Collectors.toList());
	    	    dto.setImagesPath(listProductImagePath);
	    	    dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
	    	    dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
	    	    
	    	    Brand br = brandService.findById(product.getBrandId());
	    	    if(Objects.nonNull(br)) {
	    	    	dto.setBrand(br);
	    	    }
	    	    
	    		return dto;
	    	}).collect(Collectors.toList());

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

	public ResponsePaginate paginateProductGetByCategory(Integer categoryId, Pageable page, PaginateProductRequest request){
		ResponsePaginate response = new ResponsePaginate();
		try {
			float totalElement = repo.countByCategoryIdAndDeletedAtIsNull(categoryId);
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
				
				if(Objects.nonNull(product.getCategoryId())) {
		    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
		    	    dto.setCategoryDto(categoryDto);
	    		}

				boolean isInWishList = Objects.nonNull(wishListService.findFirstByAccountIdAndProductId(request.getAccountId(), product.getProductId()));
				dto.setInWishList(isInWishList);

				List<String> listProductImagePath = productImagesService.findAllByProductId(product.getProductId())
						.stream()
						.map(ProductImage::getImagePath)
						.collect(Collectors.toList());
				dto.setImagesPath(listProductImagePath);
				dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
				dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
				
				Brand br = brandService.findById(product.getBrandId());
	    	    if(Objects.nonNull(br)) {
	    	    	dto.setBrand(br);
	    	    }
	    	    
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
	    		
	    		if(Objects.nonNull(product.getCategoryId())) {
		    		CategoryDto categoryDto = modelMapper.map(categoryService.getCategoryById(product.getCategoryId()), CategoryDto.class);
		    	    dto.setCategoryDto(categoryDto);
	    		}
	    		
	    		dto.setInWishList(true);
	    		dto.setRatingScore(productRateService.getScoreByProductId(product.getProductId()));
	    		dto.setRateTotal(productRateService.countAllByProductId(product.getProductId()));
	    		
	    		Brand br = brandService.findById(product.getBrandId());
	    	    if(Objects.nonNull(br)) {
	    	    	dto.setBrand(br);
	    	    }
	    	    
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
	    	    
	    	    Brand br = brandService.findById(product.getBrandId());
	    	    if(Objects.nonNull(br)) {
	    	    	dto.setBrand(br);
	    	    }
	    	    
	    		return dto;
	    	});
        	return productDetails.orElse(null);
        }
        return null;
    }

    public Product saveProduct (Product product){
    	product.setCreatedAt(new Date());
        return repo.save(product);
    }

    public Product updateProduct (Product product) {
        Optional<Product> existingProduct = repo.findById(product.getProductId());
        return repo.save(product);
    }

    public void deleteProductById (Integer id) {
        repo.deleteById(id);
    }

	public List<ProductDetailDto> getProductsNewArrival() {
		List<ProductDetailDto> listProductDeTail = repo.findNewArrival().stream().map(product -> {
    		ProductDetailDto dto = modelMapper.map(product, ProductDetailDto.class);
    		
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
    	    
    	    Brand br = brandService.findById(product.getBrandId());
    	    if(Objects.nonNull(br)) {
    	    	dto.setBrand(br);
    	    }
    	    
    		return dto;
    	}).collect(Collectors.toList());
		return listProductDeTail;
	}

	public List<ProductDetailDto> getProductsBestSelling() {
		List<ProductDetailDto> results = repo.findBestSelling().stream().map(p -> {
			Product product = repo.findById(Integer.valueOf(p.get("product_id").toString())).orElse(null);
			ProductDetailDto dto = 		modelMapper.map(product, ProductDetailDto.class);

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
    	    
    	    Brand br = brandService.findById(product.getBrandId());
    	    if(Objects.nonNull(br)) {
    	    	dto.setBrand(br);
    	    }
    	    
			return dto;
		}).collect(Collectors.toList());
		return results;
	}

	public Integer countByBrandId(Integer brandId) {
		return repo.countByBrandId(brandId);
	}
	
}
