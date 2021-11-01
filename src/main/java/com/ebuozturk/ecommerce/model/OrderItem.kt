package com.ebuozturk.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class OrderItem @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null,

    val quantity:Int,
    val price:Double,
    val createdDate: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name="order_id",referencedColumnName = "id")
    val order:Order,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    val product:Product
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItem

        if (id != null && id != other.id) return false
        if (quantity != other.quantity) return false
        if (price != other.price) return false
        if (createdDate != other.createdDate) return false
        if (order != other.order) return false
        if (product != other.product) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + quantity
        result = 31 * result + price.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + order.id.hashCode()
        result = 31 * result + product.id.hashCode()
        return result
    }

}
