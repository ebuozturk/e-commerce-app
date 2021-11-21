package com.ebuozturk.ecommerce.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "orders")
data class Order @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val createdDate: LocalDateTime,
    val totalPrice:Double,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    val orderAddress: Address,

    @OneToMany(mappedBy = "order" ,cascade = [CascadeType.ALL])
    val orderItems: Set<OrderItem>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    val user:User,

    @Enumerated(EnumType.ORDINAL)
    val status:Status

){
    constructor(createdDate: LocalDateTime,totalPrice: Double,address: Address,user: User): this("",createdDate,totalPrice,address,HashSet(),user,Status.PLANNING)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (id != null && id != other.id) return false
        if (createdDate != other.createdDate) return false
        if (totalPrice != other.totalPrice) return false
        if (orderItems != other.orderItems) return false
        if (orderAddress != other.orderAddress) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + totalPrice.hashCode()
        result = 31 * result + orderItems.hashCode()
        result = 31 * result + orderAddress.id.hashCode()
        result = 31 * result + user.id.hashCode()
        return result
    }

}


enum class Status(){
    PLANNING, SHIPPING, COMPLETE, CANCELLED,UNFULFILLABLE

}