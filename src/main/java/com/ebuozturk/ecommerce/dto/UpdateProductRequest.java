package com.ebuozturk.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    private Long id;
    private String name;
    private Double unitPrice;
    private Integer quantityPerUnit;
    private Integer unitsInStock;
    private Long categoryId;


}