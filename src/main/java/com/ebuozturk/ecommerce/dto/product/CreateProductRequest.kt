package com.ebuozturk.ecommerce.dto.product;


data class CreateProductRequest @JvmOverloads constructor(
    val name:String,
    val unitPrice:Double,
    val quantityPerUnit:Int,
    val unitsInStock:Int,
    val categoryId:Long,
) {
}
