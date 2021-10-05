package com.ebuozturk.ecommerce.controller;


import com.ebuozturk.ecommerce.dto.CartDto;
import com.ebuozturk.ecommerce.dto.CreateCartRequest;
import com.ebuozturk.ecommerce.dto.UpdateCartRequest;
import com.ebuozturk.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts(){
        return ResponseEntity.ok(service.getAllCarts());
    }

    @GetMapping("{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getCartById(id));
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CreateCartRequest request){
        return ResponseEntity.ok(service.createCart(request));
    }

    @PutMapping
    public ResponseEntity<CartDto> updateCart(@RequestBody UpdateCartRequest request){
        return ResponseEntity.ok(service.updateCart(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id){
        service.deleteCart(id);
        return ResponseEntity.ok().build();
    }
}
