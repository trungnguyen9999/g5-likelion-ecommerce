package com.likelion.ecommerce.repository.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.Product;
import com.likelion.ecommerce.repository.ReportRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ReportRepoImpl implements ReportRepo{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Integer getReportOfOrder(Date from, Date to) {
		StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(1) FROM orders WHERE order_time BETWEEN :from AND :to ");
        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        query.setParameter("from", from);
        query.setParameter("to", to);

        try {
            Object result = query.getSingleResult();
            if (result instanceof Number) {
                return ((Number) result).intValue();
            } else {
                // Handle unexpected result type
                throw new RuntimeException("Unexpected result type from query");
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log or rethrow)
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch order report", e);
        }
	}

	@Override
	public BigDecimal getReportOfRevenue(Date from, Date to) {
		StringBuilder queryBuilder = new StringBuilder("SELECT COALESCE(SUM(total_price), 0) FROM orders WHERE order_time BETWEEN :from AND :to ");
        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        query.setParameter("from", from);
        query.setParameter("to", to);

        try {
            Object result = query.getSingleResult();
            if (result instanceof BigDecimal) {
                return (BigDecimal) result;
            } else if (result instanceof Number) {
                return BigDecimal.valueOf(((Number) result).doubleValue());
            } else {
                throw new RuntimeException("Unexpected result type from query");
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log or rethrow)
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch revenue report", e);
        }
	}

}
