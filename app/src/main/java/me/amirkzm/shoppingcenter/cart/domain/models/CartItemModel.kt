package me.amirkzm.shoppingcenter.cart.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CartItemModel(
    val id: Int,
    val quantity: Int,
)
