package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.dto.StatusOrderDto;
import com.likelion.ecommerce.request.OrderRequest;
import com.likelion.ecommerce.request.PaginateProductRequest;
import com.likelion.ecommerce.response.OrderResponse;
import com.likelion.ecommerce.response.ResponsePaginate;
import com.likelion.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponsePaginate> getAllOrders(
            @RequestParam(required = true) Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("orderTime").descending());
        return ResponseEntity.ok().body(orderService.getAllOrders(pageable));
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-status")
    public ResponseEntity<Void> updateStatusOrder(@RequestBody StatusOrderDto statusOrder) {
        orderService.updateStatusOrder(statusOrder);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
