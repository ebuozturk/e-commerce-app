package com.ebuozturk.ecommerce.controller;


import com.ebuozturk.ecommerce.dto.cart.CartDto;
import com.ebuozturk.ecommerce.dto.cart.CreateCartRequest;
import com.ebuozturk.ecommerce.dto.cart.UpdateCartRequest;
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
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getCartById(id));
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CreateCartRequest request){
        return ResponseEntity.ok(service.createCart(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable String id, @RequestBody UpdateCartRequest request){
        return ResponseEntity.ok(service.updateCart(id,request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") String id){
        service.deleteCart(id);
        return ResponseEntity.ok().build();
    }
}
