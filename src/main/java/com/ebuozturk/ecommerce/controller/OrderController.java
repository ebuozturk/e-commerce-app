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

    @GetMapping("/user")
    public ResponseEntity<List<OrderDto>> getAllOrdersByUserId(@RequestParam("id") String id){
        return ResponseEntity.ok(service.getAllByUserId(id));
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(service.getOrderById(id));
    }
    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestParam("userId") String userId,@RequestParam("addressId") String addressId){
        return ResponseEntity.ok(service.placeOrder(userId,addressId));
    }

    @PutMapping("{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable("orderId") String orderId, @RequestParam("status") String status){
        return new ResponseEntity<>(service.updateStatus(orderId, Status.valueOf(status.toUpperCase())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable String id){
        service.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }
}
