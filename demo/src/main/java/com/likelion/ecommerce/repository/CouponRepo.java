package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    Coupon findByCode(String code);
}
