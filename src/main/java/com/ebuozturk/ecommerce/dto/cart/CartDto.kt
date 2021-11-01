package com.ebuozturk.ecommerce.dto.cart;

import java.util.*


data class CartDto @JvmOverloads constructor(
    val id:Long,
    val name:String,
    val no:String,
    val expiryDate: Date,
    val cvc:String
){

}
