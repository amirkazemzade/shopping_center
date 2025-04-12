package me.amirkzm.shoppingcenter.cart


/**
 * UI State that represents CartScreen
 **/
class CartState

/**
 * Cart Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface CartAction {
    data object OnClick : CartAction
}

