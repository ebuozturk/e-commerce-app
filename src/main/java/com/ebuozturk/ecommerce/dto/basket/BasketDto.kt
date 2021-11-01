package com.ebuozturk.ecommerce.dto.basket;

import com.ebuozturk.ecommerce.dto.basketproduct.BasketProductDto


data class BasketDto @JvmOverloads constructor(
    val id:Long,
    val userId:Long,
    val products: List<BasketProductDto>
){

}

