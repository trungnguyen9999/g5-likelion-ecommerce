package com.likelion.ecommerce.service;

import com.likelion.ecommerce.dto.StatusOrderDto;
import com.likelion.ecommerce.entities.Account;
import com.likelion.ecommerce.entities.Order;
import com.likelion.ecommerce.entities.OrderProduct;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.*;
import com.likelion.ecommerce.request.OrderDetailRequest;
import com.likelion.ecommerce.request.OrderRequest;
import com.likelion.ecommerce.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CartRepo cartRepo;

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderResponse::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("Order not found"));
        return OrderResponse.fromOrder(order);
    }

    public List<OrderResponse> getOrdersByAccountId(Integer accountId) {
        List<Order> orders = orderRepository.findOrdersByAccountId(accountId);
        return orders.stream()
                .map(OrderResponse::fromOrder)
                .collect(Collectors.toList());
    }

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Double totalPrice = 0.0;
        for (OrderDetailRequest oderDetailRequest : orderRequest.getOrderDetailRequests()) {
            totalPrice += oderDetailRequest.getPrice();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
            } else {
                email = principal.toString();
            }
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
        Account account = accountRepository.findByUsername(user.getEmail()).orElseThrow(() -> new NoSuchElementException("No exist account"));
        order.setUsertId(user.getUserId());
        order.setAccountId(account.getAccountId());
        order.setTotalPrice(totalPrice);
        order.setCurrency(orderRequest.getCurrency());
        order.setOrderTime(new Date());
        order.setPaymentType(orderRequest.getPaymentType());
        order.setAddressLine1(orderRequest.getAddressLine1());
        order.setAddressLine2(orderRequest.getAddressLine2());
        order.setApartment(orderRequest.getApartment());
        order.setSuburb(orderRequest.getSuburb());
        order.setCity(orderRequest.getCity());
        order.setRegion(orderRequest.getRegion());
        order.setStatus(orderRequest.getStatus());
        order.setDescription(orderRequest.getDescription());
        order.setCouponId(orderRequest.getCouponId());
        orderRepository.save(order);

        for (OrderDetailRequest oderDetailRequest : orderRequest.getOrderDetailRequests()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProductId(oderDetailRequest.getProductId());
            orderProduct.setQuantity(oderDetailRequest.getQuantity());
            orderProduct.setPrice(oderDetailRequest.getPrice());
            orderProductRepository.save(orderProduct);
        }

        cartRepo.deleteCartByAccountId(account.getAccountId());

    }

    public void updateStatusOrder(StatusOrderDto statusOrder) {
        Order order = orderRepository.findById(statusOrder.getOrderId()).orElseThrow(() -> new NoSuchElementException("Order not found"));
        order.setStatus(statusOrder.getStatus());
        orderRepository.save(order);
    }
}
