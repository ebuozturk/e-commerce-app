package com.ebuozturk.ecommerce.dto.basketproduct;

import com.ebuozturk.ecommerce.dto.product.ProductDto


data class BasketProductDto @JvmOverloads constructor(
    val id:String,
    val quantity:Int,
    val basketId:String,
    val product: ProductDto
){

}