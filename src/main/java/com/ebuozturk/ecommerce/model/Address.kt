package com.ebuozturk.ecommerce.model;

import kotlin.jvm.JvmOverloads;

import javax.persistence.*;


@Entity
data class Address @JvmOverloads constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long? = null,
        val addressName:String,
        val phoneNumber:String,
        val country:String,
        val city:String,
        val street:String,
        val zipCode:String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id",referencedColumnName = "id")
        val user:User
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (id != null && id != other.id) return false
        if (addressName != other.addressName) return false
        if (phoneNumber != other.phoneNumber) return false
        if (country != other.country) return false
        if (city != other.city) return false
        if (street != other.street) return false
        if (zipCode != other.zipCode) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + addressName.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + street.hashCode()
        result = 31 * result + zipCode.hashCode()
        result = 31 * result + user.id.hashCode()
        return result
    }
}
