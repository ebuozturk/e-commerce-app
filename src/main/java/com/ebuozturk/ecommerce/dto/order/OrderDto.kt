package com.ebuozturk.ecommerce.dto.order

import com.ebuozturk.ecommerce.dto.orderitem.OrderItemDto
import java.time.LocalDateTime

data class OrderDto @JvmOverloads constructor(
    val id:Long,
    val createdDate: LocalDateTime,
    val totalPrice: Double,
    val items: List<OrderItemDto>,
    val status:String
){}
