package com.likelion.ecommerce.response;

import lombok.Data;

@Data
public class OrderStatusCountReponse {
    private Integer processing;
    private Integer completed;
    private Integer cancelled;
}
