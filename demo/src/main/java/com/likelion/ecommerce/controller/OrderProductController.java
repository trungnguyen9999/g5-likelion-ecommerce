package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.entities.OrderProduct;
import com.likelion.ecommerce.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-products")
public class OrderProductController {

    private final OrderProductService orderProductService;

    @GetMapping
    public ResponseEntity<List<OrderProduct>> getAllOrderProducts() {
        return ResponseEntity.ok(orderProductService.getAllOrderProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderProduct> getOrderProductById(@PathVariable Integer id) {
        Optional<OrderProduct> orderProduct = orderProductService.getOrderProductById(id);
        return orderProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<OrderProduct> createOrderProduct(@RequestBody OrderProduct orderProduct) {
        OrderProduct createdOrderProduct = orderProductService.createOrderProduct(orderProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderProduct> updateOrderProduct(@PathVariable Integer id, @RequestBody OrderProduct orderProduct) {
        try {
            OrderProduct updatedOrderProduct = orderProductService.updateOrderProduct(id, orderProduct);
            return ResponseEntity.ok(updatedOrderProduct);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Integer id) {
        try {
            orderProductService.deleteOrderProduct(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
