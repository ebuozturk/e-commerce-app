package com.ebuozturk.ecommerce.dto.user;

data class UpdateUserRequest @JvmOverloads constructor(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val email: String
) {

}
