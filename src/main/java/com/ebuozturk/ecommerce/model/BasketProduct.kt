package com.ebuozturk.ecommerce.model;

import javax.persistence.*;

@Entity

data class BasketProduct @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null,
    @OneToOne
    @JoinColumn(name = "product_id")
    val product:Product,
    @ManyToOne
    @JoinColumn(name = "basket_id")
    val basket:Basket,
    var quantity:Int = 1

) {

    fun increaseQuantity(){
        this.quantity ++;
    }
    fun decreaseQuantity(){
        if(this.quantity>1){
            this.quantity --;
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BasketProduct

        if (id != null && id != other.id) return false
        if (product != other.product) return false
        if (basket != other.basket) return false
        if (quantity != other.quantity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + product.id.hashCode()
        result = 31 * result + basket.id.hashCode()
        result = 31 * result + quantity
        return result
    }


}
