package com.likelion.ecommerce.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.repository.ProductRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Product> filterProduct(String name, Long fromPrice, Long toPrice, String sort, Integer limit, Integer offset) {
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM products WHERE name ILIKE :name AND price BETWEEN :fromPrice AND :toPrice");

        if (sort != null && !sort.isEmpty()) {
            queryBuilder.append(sort);
        }

        queryBuilder.append(" LIMIT :limit OFFSET :offset");

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Product.class);
        query.setParameter("name", "%" + name + "%");
        query.setParameter("fromPrice", fromPrice);
        query.setParameter("toPrice", toPrice);
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);

        return query.getResultList();
	}

}
