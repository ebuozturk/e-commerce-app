package com.ebuozturk.ecommerce.dto.cart;

import java.util.*


data class CartDto @JvmOverloads constructor(
    val id:String,
    val name:String,
    val no:String,
    val expiryDate: Date,
    val cvc:String
){

}
