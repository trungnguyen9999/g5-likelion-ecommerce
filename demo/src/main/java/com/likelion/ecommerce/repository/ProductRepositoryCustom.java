package com.likelion.ecommerce.repository;

import java.util.List;

import com.likelion.ecommerce.entities.Product;

public interface ProductRepositoryCustom {
	List<Product> filterProduct(String name, int categoryId, Long fromPrice, Long toPrice, String sort, Integer limit, Integer offset, List<Integer> brandIds);
}
