package me.amirkzm.shoppingcenter.cart.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)
