package com.ebuozturk.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends NotFoundException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
