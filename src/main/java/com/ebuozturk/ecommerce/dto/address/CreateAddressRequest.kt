package com.ebuozturk.ecommerce.dto.address;

data class CreateAddressRequest @JvmOverloads constructor(
    val addressName: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
    val street: String,
    val zipCode: String,
    val userId: String
){
}
