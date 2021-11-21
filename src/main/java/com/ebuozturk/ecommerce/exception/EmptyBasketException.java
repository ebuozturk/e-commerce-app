package com.ebuozturk.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyBasketException extends RuntimeException{
    public EmptyBasketException(String message) {
        super(message);
    }
}
