package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Brand;
import com.likelion.ecommerce.entities.Coupon;
import com.likelion.ecommerce.request.PaginateProductRequest;
import com.likelion.ecommerce.response.ResponsePaginate;
import com.likelion.ecommerce.response.ResponseStandard;
import com.likelion.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
@Validated
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/paginate")
    public ResponseEntity<ResponsePaginate> getAllProductInWishList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "true") Boolean active
    )
    {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("created_at").descending());
        return ResponseEntity.ok()
                .body(couponService.paginateCoupon(pageable, active));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseStandard> saveCoupon(@RequestBody Coupon coupon)
    {
        ResponseStandard rp = new ResponseStandard();

        rp.setMessage("Insert successful!");
        rp.setData(couponService.save(coupon));
        return ResponseEntity.ok().body(rp);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStandard> updateCoupon(@RequestBody Coupon coupon)
    {
        ResponseStandard rp = new ResponseStandard();

        rp.setMessage("Update successful!");
        rp.setData(couponService.update(coupon));
        return ResponseEntity.ok().body(rp);
    }

    @GetMapping("/find-by-code")
    public ResponseEntity<Coupon> findByCode(@RequestParam String code)
    {
        return ResponseEntity.ok()
                .body(couponService.findByCode(code));
    }
}
