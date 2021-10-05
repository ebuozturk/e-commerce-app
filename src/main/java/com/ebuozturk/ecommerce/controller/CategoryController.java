package com.ebuozturk.ecommerce.controller;

import com.ebuozturk.ecommerce.dto.CategoryDto;
import com.ebuozturk.ecommerce.dto.CreateCategoryRequest;
import com.ebuozturk.ecommerce.dto.UpdateCategoryRequest;
import com.ebuozturk.ecommerce.model.Category;
import com.ebuozturk.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getCategoryById(id));
    }
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryRequest request){
        return ResponseEntity.ok(service.createCategory(request));
    }
    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody UpdateCategoryRequest request){
        return ResponseEntity.ok(service.updateCategory(request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
