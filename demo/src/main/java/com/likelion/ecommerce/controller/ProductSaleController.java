package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.Brand;
import com.likelion.ecommerce.response.ResponseStandard;
import com.likelion.ecommerce.service.ProductSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-sale")
public class ProductSaleController {

    private final ProductSaleService productSaleService;

    @GetMapping("/public/all")
    public ResponseEntity<ResponseStandard> getBrandList()
    {
//        List<Brand> data = brandService.findAll();
        ResponseStandard rp = new ResponseStandard();
//        rp.setMessage(Objects.isNull(data) ? "Không tìm thấy dữ liệu" : "Thành công");
//        rp.setData(data);
        return ResponseEntity.ok().body(rp);
    }
}
