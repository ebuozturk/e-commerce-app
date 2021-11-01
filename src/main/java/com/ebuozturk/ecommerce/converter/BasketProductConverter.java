package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.basketproduct.BasketProductDto;
import com.ebuozturk.ecommerce.model.BasketProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BasketProductConverter {
    private final ProductConverter productConverter;

    public BasketProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public BasketProductDto convert(BasketProduct product){
        return new BasketProductDto(product.getId(),product.getQuantity(),product.getBasket().getId(),productConverter.convert(product.getProduct()));
    }

    public List<BasketProductDto> convert(Set<BasketProduct> products){
        return products.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<BasketProductDto> convert(List<BasketProduct> products){
        return products.stream().map(this::convert).collect(Collectors.toList());
    }
}