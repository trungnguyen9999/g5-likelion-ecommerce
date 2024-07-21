package com.likelion.ecommerce.response;

import com.likelion.ecommerce.entities.OrderProduct;
import lombok.Data;

@Data
public class OrderDetailResponse {
    private Integer productId;

    private Integer quantity;

    private Double price;

    public static OrderDetailResponse fromOrderProduct(OrderProduct orderProduct) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setProductId(orderProduct.getProductId());
        response.setQuantity(orderProduct.getQuantity());
        response.setPrice(orderProduct.getPrice());
        return response;
    }
}
