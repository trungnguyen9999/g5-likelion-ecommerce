package com.likelion.ecommerce.request;

import lombok.Data;

@Data
public class CartRequest {
    private Integer productId;

    private Integer quantity;
}
