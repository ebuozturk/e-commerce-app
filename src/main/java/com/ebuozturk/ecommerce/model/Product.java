package com.ebuozturk.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double unitPrice;
    private Integer quantityPerUnit;
    private Integer unitsInStock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    

    public Product(String name, Double unitPrice, Integer quantityPerUnit, Integer unitsInStock) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
        this.unitsInStock = unitsInStock;
    }

    public Product(String name, Double unitPrice, Integer quantityPerUnit, Integer unitsInStock, Category category) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
        this.unitsInStock = unitsInStock;
        this.category = category;
    }
}
