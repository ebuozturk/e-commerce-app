package com.ebuozturk.ecommerce.service;


import com.ebuozturk.ecommerce.converter.CartConverter;
import com.ebuozturk.ecommerce.dto.CartDto;
import com.ebuozturk.ecommerce.dto.CreateCartRequest;
import com.ebuozturk.ecommerce.dto.UpdateCartRequest;
import com.ebuozturk.ecommerce.exception.CartNotFoundException;
import com.ebuozturk.ecommerce.model.Cart;
import com.ebuozturk.ecommerce.model.User;
import com.ebuozturk.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartConverter cartConverter;

    public CartService(CartRepository cartRepository, UserService userService, CartConverter cartConverter) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.cartConverter = cartConverter;
    }

    public List<CartDto> getAllCarts(){
        return cartConverter.convert(cartRepository.findAll());
    }

    public CartDto getCartById(final Long id){
        return cartConverter.convert(findById(id));
    }
    public List<CartDto> getCartsByUserId(final Long userId){
        return cartConverter.convert(cartRepository.findByUser_id(userId));
    }
    public CartDto createCart(final CreateCartRequest request){
        User user = userService.findById(request.getUserId());
        return cartConverter.convert(cartRepository.save(new Cart(request.getName(),
                request.getNo(), request.getExpiryDate(), request.getCvc(),user)));
    }

    public CartDto updateCart(final UpdateCartRequest request){
        User user = userService.findById(request.getUserId());
        findById(request.getId());
        return cartConverter.convert(cartRepository.save(new Cart(request.getId(),request.getName(),
                request.getNo(), request.getExpiryDate(), request.getCvc(),user)));
    }

    public void deleteCart(final Long id){
        if(doesCartExist(id)){
            cartRepository.deleteById(id);
        }else
            throw new CartNotFoundException("Cart is not found by following id: "+id);
    }

    protected Boolean doesCartExist(final Long id){
        return cartRepository.existsById(id);
    }
    protected Cart findById(Long id){
        return cartRepository.findById(id).orElseThrow(()->new CartNotFoundException("Cart is not found by following id: "+id));
    }
}
