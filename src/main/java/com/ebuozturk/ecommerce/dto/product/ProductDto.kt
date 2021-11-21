package com.ebuozturk.ecommerce.dto.product;

import com.ebuozturk.ecommerce.dto.category.CategoryDto

data class ProductDto @JvmOverloads constructor(
    val id: String? = "",
    val name: String,
    val unitPrice: Double,
    val quantityPerUnit: Int,
    val unitsInStock: Int ,
    val category: CategoryDto
){

}

