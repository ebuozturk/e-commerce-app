package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.CartDto;
import com.ebuozturk.ecommerce.model.Cart;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {

    public CartDto convert(Cart cart){
        return new CartDto( cart.getId(),
                            cart.getName(),
                            cart.getNo(),
                            cart.getExpiryDate(),
                            cart.getCvc()
                );
    }

    public List<CartDto> convert(List<Cart> cartList){
        return cartList.stream().map(this::convert).collect(Collectors.toList());
    }

}
