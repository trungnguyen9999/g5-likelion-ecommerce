package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.dto.StatusOrderDto;
import com.likelion.ecommerce.request.OrderRequest;
import com.likelion.ecommerce.response.OrderResponse;
import com.likelion.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest oderRequest) {
        orderService.createOrder(oderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/by-accountID/{accountId}")
    public ResponseEntity<List<OrderResponse>> getOrderByAccountId(@PathVariable Integer accountId) {
        try {
            return ResponseEntity.ok(orderService.getOrdersByAccountId(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update-status")
    public ResponseEntity<Void> updateStatusOrder(@RequestBody StatusOrderDto statusOrder) {
        orderService.updateStatusOrder(statusOrder);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
