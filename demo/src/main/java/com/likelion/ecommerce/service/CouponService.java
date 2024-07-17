package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.Coupon;
import com.likelion.ecommerce.repository.CouponRepo;
import com.likelion.ecommerce.response.ResponsePaginate;
import com.likelion.ecommerce.response.ResponseStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepo repo;

    public ResponsePaginate paginateCoupon(Pageable page, Boolean active){
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
            response.setItems(repo.paginateCoupon(page, active).getContent());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Coupon save(Coupon coupon) {
        return repo.save(coupon);
    }

    public Coupon update(Coupon coupon) {
        return repo.save(coupon);
    }

    public Coupon findByCode(String code) {
        return repo.findByCode(code);
    }
}
