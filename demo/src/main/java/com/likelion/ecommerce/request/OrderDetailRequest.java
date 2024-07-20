package com.likelion.ecommerce.request;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private Integer productId;

    private Integer quantity;

    private Double price;

}
