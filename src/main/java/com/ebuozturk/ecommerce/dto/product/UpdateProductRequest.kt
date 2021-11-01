package com.ebuozturk.ecommerce.dto.product;

data class UpdateProductRequest @JvmOverloads constructor(
    val id: Long,
    val name: String,
    val unitPrice: Double,
    val quantityPerUnit: Int,
    val unitsInStock: Int,
    val categoryId: Long
){

}