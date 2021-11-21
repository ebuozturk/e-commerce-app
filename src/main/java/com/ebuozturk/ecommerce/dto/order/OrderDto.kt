package com.ebuozturk.ecommerce.dto.order

import com.ebuozturk.ecommerce.dto.address.AddressDto
import com.ebuozturk.ecommerce.dto.orderitem.OrderItemDto
import java.time.LocalDateTime

data class OrderDto @JvmOverloads constructor(
    val id: String? = "",
    val createdDate: LocalDateTime,
    val totalPrice: Double,
    val items: List<OrderItemDto>,
    val address: AddressDto,
    val status:String
){}
