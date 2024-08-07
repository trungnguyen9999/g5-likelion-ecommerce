package com.likelion.ecommerce.request;

import com.likelion.ecommerce.entities.ECurrency;
import com.likelion.ecommerce.entities.EPaymentType;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String country;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private ECurrency currency;

    private EPaymentType paymentType;

    private String addressLine1;

    private String addressLine2;

    private String apartment;

    private String suburb;

    private String city;

    private String region;

    private Integer status = 1;

    private String description;

    private Integer couponId;

    private Double totalPrice;

    List<OrderDetailRequest> orderDetailRequests;
}
