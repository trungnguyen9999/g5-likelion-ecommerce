package com.likelion.ecommerce.service;

import com.likelion.ecommerce.repository.ProductSaleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSaleService {

    private final ProductSaleRepo productSaleRepo;


}
