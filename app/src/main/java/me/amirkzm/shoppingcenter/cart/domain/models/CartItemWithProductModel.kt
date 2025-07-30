package me.amirkzm.shoppingcenter.cart.domain.models

import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel

data class CartItemWithProductModel(
    val product: ProductItemModel?,
    val quantity: Int,
) {
    val totalPrice: Double?
        get() = product?.price?.times(quantity)
}