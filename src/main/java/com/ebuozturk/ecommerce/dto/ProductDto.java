package com.ebuozturk.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Double unitPrice;
    private Integer quantityPerUnit;
    private Integer unitsInStock;
    private CategoryDto category;
}

