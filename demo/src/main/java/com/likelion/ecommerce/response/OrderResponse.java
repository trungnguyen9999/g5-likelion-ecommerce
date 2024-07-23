package com.likelion.ecommerce.response;

import com.likelion.ecommerce.entities.ECurrency;
import com.likelion.ecommerce.entities.EPaymentType;
import com.likelion.ecommerce.entities.Order;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderResponse {
    private Integer usertId;

    private String country;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Double totalPrice;

    private ECurrency currency;

    private Date orderTime;

    private EPaymentType paymentType;

    private String addressLine1;

    private String addressLine2;

    private String apartment;

    private String suburb;

    private String city;

    private String region;

    private Integer status;

    private String description;

    private Integer couponId;

    @Setter
    List<OrderDetailResponse> orderDetailResponses;

    public static OrderResponse fromOrder(Order order) {
        OrderResponse response = new OrderResponse();
        response.setUsertId(order.getUsertId());
        response.setTotalPrice(order.getTotalPrice());
        response.setCurrency(order.getCurrency());
        response.setOrderTime(order.getOrderTime());
        response.setPaymentType(order.getPaymentType());
        response.setAddressLine1(order.getAddressLine1());
        response.setAddressLine2(order.getAddressLine2());
        response.setApartment(order.getApartment());
        response.setSuburb(order.getSuburb());
        response.setCity(order.getCity());
        response.setRegion(order.getRegion());
        response.setStatus(order.getStatus());
        response.setDescription(order.getDescription());
        response.setCouponId(order.getCouponId());
        response.setCountry(order.getCountry());
        response.setFirstName(order.getFirstName());
        response.setLastName(order.getLastName());
        response.setPhoneNumber(order.getPhoneNumber());
        response.setOrderDetailResponses(order.getOrderProducts().stream()
                .map(OrderDetailResponse::fromOrderProduct)
                .collect(Collectors.toList()));
        return response;
    }

}
