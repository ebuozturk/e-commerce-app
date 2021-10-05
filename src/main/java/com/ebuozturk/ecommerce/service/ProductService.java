package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.ProductConverter;
import com.ebuozturk.ecommerce.dto.CreateProductRequest;
import com.ebuozturk.ecommerce.dto.ProductDto;
import com.ebuozturk.ecommerce.dto.UpdateProductRequest;
import com.ebuozturk.ecommerce.exception.ProductNotFoundException;
import com.ebuozturk.ecommerce.model.Category;
import com.ebuozturk.ecommerce.model.Product;
import com.ebuozturk.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;


    public ProductService(ProductRepository productRepository, ProductConverter productConverter, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.categoryService = categoryService;
    }

    public ProductDto getProductById(Long id){
        return productConverter.convert(findById(id));
    }
    public List<ProductDto> getAllProducts(){
        return productConverter.convert(productRepository.findAll());
    }

    public List<ProductDto> getProductsByCategoryId(Long categoryId){
        categoryService.findById(categoryId);
        return productConverter.convert(productRepository.findByCategory_id(categoryId));
    }
    public ProductDto createProduct(final CreateProductRequest request){
        Category category = categoryService.findById(request.getCategoryId());
        return productConverter.convert(productRepository.save(new Product(request.getName(),
                request.getUnitPrice(),request.getQuantityPerUnit(), request.getUnitsInStock(),category)));
    }
    public ProductDto updateProduct(final UpdateProductRequest request){
        Category category = categoryService.findById(request.getCategoryId());
        findById(request.getId());
        return productConverter.convert(productRepository.save(new Product(request.getId(),request.getName(),
                request.getUnitPrice(),request.getQuantityPerUnit(), request.getUnitsInStock(),category)));
    }
    public void deleteProduct(Long id){
        if(doesProductExist(id)){
           productRepository.deleteById(id);
        }else
            throw new ProductNotFoundException("Product isn't found by following id: "+id);
    }
    protected Boolean doesProductExist(Long id){
        return productRepository.existsById(id);
    }
    protected Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product isn't found by following id: "+id));
    }
}
