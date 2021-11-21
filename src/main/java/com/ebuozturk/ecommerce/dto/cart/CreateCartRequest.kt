package com.ebuozturk.ecommerce.dto.cart;

import java.util.*

data class CreateCartRequest @JvmOverloads constructor(
    val name: String,
    val no: String,
    val expiryDate: Date,
    val cvc: String,
    val userId: String
){
}
