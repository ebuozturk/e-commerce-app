package com.ebuozturk.ecommerce.dto.basketproduct;

import com.ebuozturk.ecommerce.dto.product.ProductDto


data class BasketProductDto @JvmOverloads constructor(
    val id:Long,
    val quantity:Int,
    val basketId:Long,
    val product: ProductDto
){

}