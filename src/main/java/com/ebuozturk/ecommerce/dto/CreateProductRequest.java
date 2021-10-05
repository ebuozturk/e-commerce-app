package com.ebuozturk.ecommerce.dto;


import com.ebuozturk.ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String name;
    private Double unitPrice;
    private Integer quantityPerUnit;
    private Integer unitsInStock;
    private Long categoryId;


}
