package com.likelion.ecommerce.request;

import lombok.Data;

@Data
public class CartRequest {
    private Integer cartId;

    private Integer productId;

    private Integer quantity;
}
