package com.ebuozturk.ecommerce.controller;

import com.ebuozturk.ecommerce.dto.basketproduct.BasketProductDto;
import com.ebuozturk.ecommerce.service.BasketProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/basketProduct")
public class BasketProductController {
    private final BasketProductService basketProductService;

    public BasketProductController(BasketProductService basketProductService) {
        this.basketProductService = basketProductService;
    }

    @PatchMapping("/increase")
    public ResponseEntity<BasketProductDto> increaseBasketProduct(@RequestParam("id") String id){
        return ResponseEntity.ok(basketProductService.increaseQuantityByProductId(id));
    }
    @PatchMapping("/decrease")
    public ResponseEntity<BasketProductDto> decreaseBasketProduct(@RequestParam("id") String id){
        return ResponseEntity.ok(basketProductService.decreaseQuantityByProductId(id));
    }
}
