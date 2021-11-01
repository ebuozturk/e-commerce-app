package com.ebuozturk.ecommerce.dto.user;

data class UserDto @JvmOverloads constructor(
    val id: Long? = null,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val email: String
){

}
