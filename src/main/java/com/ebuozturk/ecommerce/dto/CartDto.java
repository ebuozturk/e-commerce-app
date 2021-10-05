package com.ebuozturk.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;
    private String name;
    private String no;
    private Date expiryDate;
    private String cvc;
}
