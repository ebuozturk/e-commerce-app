package com.ebuozturk.ecommerce.dto.basket;

import com.ebuozturk.ecommerce.dto.basketproduct.BasketProductDto


data class BasketDto @JvmOverloads constructor(
    val id:String,
    val userId:String,
    val products: List<BasketProductDto>
    ){

}

