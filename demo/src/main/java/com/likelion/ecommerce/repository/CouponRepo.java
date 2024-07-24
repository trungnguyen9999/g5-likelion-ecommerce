package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCode(String code);

    @Query(value =  "SELECT * FROM coupon c where (quantity > quantity_used) = :active ",
            countQuery = "SELECT count(1) FROM coupon c where (quantity > quantity_used) = :active ", nativeQuery = true)
    Page<Coupon> paginateCoupon(Pageable pageable, Boolean active);

    Boolean existsByCode(String code);
}
