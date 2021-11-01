package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.basket.BasketDto;
import com.ebuozturk.ecommerce.model.Basket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketConverter {

    private final BasketProductConverter productConverter;

    public BasketConverter(BasketProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public BasketDto convert(Basket basket){
        return new BasketDto(basket.getId(), basket.getUser().getId(),productConverter.convert(basket.getProducts()));
    }
    public List<BasketDto> convert(List<Basket> baskets){
        return baskets.stream().map(this::convert).collect(Collectors.toList());
    }
}

