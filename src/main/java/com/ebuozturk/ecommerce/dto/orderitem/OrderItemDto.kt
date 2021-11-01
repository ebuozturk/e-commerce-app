package com.ebuozturk.ecommerce.dto.orderitem

import com.ebuozturk.ecommerce.dto.product.ProductDto
import java.time.LocalDateTime

data class OrderItemDto @JvmOverloads constructor (
    val id:Long,
    val createdDate:LocalDateTime,
    val quantity:Int,
    val product:ProductDto
){}
