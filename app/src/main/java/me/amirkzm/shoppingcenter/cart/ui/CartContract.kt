package me.amirkzm.shoppingcenter.cart.ui

import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel


/**
 * UI State that represents CartScreen
 **/
data class CartItemState(
    val productItemModel: ProductItemModel,
    val quantity: Int = 1,
) {
    val totalPrice: Double
        get() = productItemModel.price * quantity
}

data class CartState(
    val items: List<CartItemState> = emptyList(),
)

/**
 * Cart Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface CartAction {
    data class OnItemClick(val id: Int) : CartAction
    data class OnIncreaseItemQuantity(val id: Int) : CartAction
    data class OnDecreaseItemQuantity(val id: Int) : CartAction
    data class OnRemoveItem(val id: Int) : CartAction
    data object OnCheckOut : CartAction
}

