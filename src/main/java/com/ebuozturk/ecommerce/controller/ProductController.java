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
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("{id}/category")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getProductsByCategoryId(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest request){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request){
        return ResponseEntity.ok(service.updateProduct(id,request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id){
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
