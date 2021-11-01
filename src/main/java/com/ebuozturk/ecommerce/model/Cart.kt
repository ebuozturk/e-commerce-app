package com.ebuozturk.ecommerce.model;

import java.util.*
import javax.persistence.*;

@Entity
data class Cart @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?= null,
    val name: String,
    val no: String,
    val expiryDate: Date,
    val cvc: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    val user: User
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cart

        if (id != null && id != other.id) return false
        if (name != other.name) return false
        if (no != other.no) return false
        if (expiryDate != other.expiryDate) return false
        if (cvc != other.cvc) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + no.hashCode()
        result = 31 * result + expiryDate.hashCode()
        result = 31 * result + cvc.hashCode()
        result = 31 * result + user.id.hashCode()
        return result
    }
}
