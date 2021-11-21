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
    public ResponseEntity<BasketDto> getBasketByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(service.getBasketByUserId(userId));
    }
    @GetMapping
    public ResponseEntity<List<BasketDto>> getAllBaskets(){
        return  ResponseEntity.ok(service.getAllBaskets());
    }

    @PostMapping
    public ResponseEntity<BasketDto> addProductToUserBasket(@RequestParam("userId") String userId, @RequestParam("productId") String productId){

        return ResponseEntity.ok(service.addProductToBasket(userId,productId));
    }
    @DeleteMapping
    public ResponseEntity<Void> removeProductFromUserBasket(@RequestParam("userId") String userId, @RequestParam("productId") String productId){
        service.removeProductFromBasket(userId,productId);
        return ResponseEntity.ok().build();
    }

}
