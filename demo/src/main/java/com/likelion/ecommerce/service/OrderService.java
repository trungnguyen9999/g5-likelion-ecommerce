package com.likelion.ecommerce.service;

import com.likelion.ecommerce.entities.Order;
import com.likelion.ecommerce.entities.OrderProduct;
import com.likelion.ecommerce.entities.User;
import com.likelion.ecommerce.repository.OrderProductRepository;
import com.likelion.ecommerce.repository.OrderRepository;
import com.likelion.ecommerce.repository.UserRepository;
import com.likelion.ecommerce.request.OrderDetailRequest;
import com.likelion.ecommerce.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Double totalPrice = 0.0;
        for (OrderDetailRequest oderDetailRequest : orderRequest.getOderDetailRequests()) {
            totalPrice += oderDetailRequest.getPrice();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
        order.setUsertId(user.getUserId());
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

        Order existingOrder = orderRepository.findTopByOrderByIdDesc().orElseThrow(NoSuchElementException::new);
        OrderProduct orderProduct = new OrderProduct();
        for (OrderDetailRequest oderDetailRequest : orderRequest.getOderDetailRequests()) {
            orderProduct.setOrderId(existingOrder.getOrderId());
            orderProduct.setProductId(oderDetailRequest.getProductId());
            orderProduct.setQuantity(oderDetailRequest.getQuantity());
            orderProduct.setPrice(oderDetailRequest.getPrice());
            orderProductRepository.save(orderProduct);
        }
    }

    public Order updateOrder(Integer orderId, Order order) {
        if (orderRepository.existsById(orderId)) {
            order.setOrderId(orderId);
            return orderRepository.save(order);
        } else {
            throw new NoSuchElementException("Order not found with id " + orderId);
        }
    }

    public void deleteOrder(Integer orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NoSuchElementException("Order not found with id " + orderId);
        }
    }
}
