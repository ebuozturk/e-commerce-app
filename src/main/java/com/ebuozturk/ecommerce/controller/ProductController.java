package com.ebuozturk.ecommerce.controller;


import com.ebuozturk.ecommerce.dto.product.CreateProductRequest;
import com.ebuozturk.ecommerce.dto.product.ProductDto;
import com.ebuozturk.ecommerce.dto.product.UpdateProductRequest;
import com.ebuozturk.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("{id}/category")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getProductsByCategoryId(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest request){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody UpdateProductRequest request){
        return ResponseEntity.ok(service.updateProduct(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
