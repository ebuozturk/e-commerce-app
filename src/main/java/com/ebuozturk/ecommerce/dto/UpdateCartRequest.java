package com.ebuozturk.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest {
    private Long id;
    private String name;
    private String no;
    private Date expiryDate;
    private String cvc;
    private Long userId;
}