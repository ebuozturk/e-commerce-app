package com.ebuozturk.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BasketProductNotFoundException extends RuntimeException {

    public BasketProductNotFoundException(String message) {
        super(message);
    }
}

