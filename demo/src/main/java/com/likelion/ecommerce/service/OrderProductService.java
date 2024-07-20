package com.likelion.ecommerce.service;


import com.likelion.ecommerce.entities.OrderProduct;
import com.likelion.ecommerce.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    public Optional<OrderProduct> getOrderProductById(Integer id) {
        return orderProductRepository.findById(id);
    }

    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    public OrderProduct updateOrderProduct(Integer id, OrderProduct orderProduct) {
        if (orderProductRepository.existsById(id)) {
            orderProduct.setId(id);
            return orderProductRepository.save(orderProduct);
        } else {
            throw new NoSuchElementException("OrderProduct not found with id " + id);
        }
    }

    public void deleteOrderProduct(Integer id) {
        if (orderProductRepository.existsById(id)) {
            orderProductRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("OrderProduct not found with id " + id);
        }
    }
}
