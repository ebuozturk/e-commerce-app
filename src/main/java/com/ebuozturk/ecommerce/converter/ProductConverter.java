package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.product.ProductDto;
import com.ebuozturk.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    private final CategoryConverter categoryConverter;

    public ProductConverter(CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    public ProductDto convert(Product product){
        return new ProductDto(product.getId(),
                product.getName(),
                product.getUnitPrice(),
                product.getQuantityPerUnit(),
                product.getUnitsInStock(),
                categoryConverter.convert(product.getCategory())
                );
    }

    public List<ProductDto> convert(List<Product> productList){
        return productList.stream().map(this::convert).collect(Collectors.toList());
    }
}
