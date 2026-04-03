package com.ecommerce.monolith.order.controller;

import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.dto.OrderRequest;
import com.ecommerce.monolith.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getCustomerOrderHistory(@PathVariable Long customerId) {
        List<OrderDTO> history = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(history);
    }
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest request) {
        OrderDTO createdOrder = orderService.createOrder(request);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}