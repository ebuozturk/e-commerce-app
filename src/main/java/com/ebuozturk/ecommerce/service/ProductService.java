package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.ProductConverter;
import com.ebuozturk.ecommerce.dto.product.CreateProductRequest;
import com.ebuozturk.ecommerce.dto.product.ProductDto;
import com.ebuozturk.ecommerce.dto.product.UpdateProductRequest;
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

    public ProductDto getProductById(String id){
        return productConverter.convert(findById(id));
    }
    public List<ProductDto> getAllProducts(){
        return productConverter.convert(productRepository.findAll());
    }

    public List<ProductDto> getProductsByCategoryId(String categoryId){
        categoryService.findById(categoryId);
        return productConverter.convert(productRepository.findByCategory_id(categoryId));
    }
    public ProductDto createProduct(final CreateProductRequest request){
        Category category = categoryService.findById(request.getCategoryId());
        return productConverter.convert(productRepository.save(new Product(request.getName(),
                request.getUnitPrice(),request.getQuantityPerUnit(), request.getUnitsInStock(),category)));
    }
    public ProductDto updateProduct(final String id,final UpdateProductRequest request){
        Category category = categoryService.findById(request.getCategoryId());
        findById(id);
        return productConverter.convert(productRepository.save(new Product(id,request.getName(),
                request.getUnitPrice(),request.getQuantityPerUnit(), request.getUnitsInStock(),category)));
    }
    public void deleteProduct(String id){
        if(doesProductExist(id)){
           productRepository.deleteById(id);
        }else
            throw new ProductNotFoundException("Product isn't found by following id: "+id);
    }
    protected Boolean doesProductExist(String id){
        return productRepository.existsById(id);
    }
    protected Product findById(String id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product isn't found by following id: "+id));
    }
}
