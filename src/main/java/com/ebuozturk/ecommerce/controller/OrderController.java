package com.ebuozturk.ecommerce.controller;

import com.ebuozturk.ecommerce.dto.order.OrderDto;
import com.ebuozturk.ecommerce.model.Order;
import com.ebuozturk.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("{userId}")
    public ResponseEntity<List<OrderDto>> placeOrder(@PathVariable Long userId){
        return ResponseEntity.ok(service.placeOrder(userId));
    }
}
