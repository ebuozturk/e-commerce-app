package com.ebuozturk.ecommerce.controller;

import com.ebuozturk.ecommerce.dto.order.OrderDto;
import com.ebuozturk.ecommerce.model.Order;
import com.ebuozturk.ecommerce.model.Status;
import com.ebuozturk.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestParam("status") String status){
        return new ResponseEntity<>(service.updateStatus(orderId, Status.valueOf(status.toUpperCase())), HttpStatus.ACCEPTED);
    }
}
