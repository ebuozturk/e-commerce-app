package com.ebuozturk.ecommerce.model;

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*;

@Entity
data class Product @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val name: String,
    val unitPrice: Double,
    val quantityPerUnit: Int,
    val unitsInStock: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",referencedColumnName = "id")
    val category: Category

){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != null && id != other.id) return false
        if (name != other.name) return false
        if (unitPrice != other.unitPrice) return false
        if (quantityPerUnit != other.quantityPerUnit) return false
        if (unitsInStock != other.unitsInStock) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + unitPrice.hashCode()
        result = 31 * result + quantityPerUnit
        result = 31 * result + unitsInStock
        result = 31 * result + category.id.hashCode()
        return result
    }
}
