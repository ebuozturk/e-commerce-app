package com.ebuozturk.ecommerce.controller;

import com.ebuozturk.ecommerce.dto.basket.BasketDto;
import com.ebuozturk.ecommerce.service.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/basket")
public class BasketController {
    private final BasketService service;

    public BasketController(BasketService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BasketDto> getBasketByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(service.getBasketByUserId(userId));
    }
    @GetMapping
    public ResponseEntity<List<BasketDto>> getAllBaskets(){
        return  ResponseEntity.ok(service.getAllBaskets());
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<BasketDto> addProductToUserBasket(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId){

        return ResponseEntity.ok(service.addProductToBasket(userId,productId));
    }
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> removeProductFromUserBasket(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId){
        service.removeProductFromBasket(userId,productId);
        return ResponseEntity.ok().build();
    }

}
