package me.amirkzm.shoppingcenter.cart.ui

import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.domain.models.isSuccess
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrThrow
import java.util.Locale


val RequestState<List<CartItemWithProductModel>>.isFullyLoaded: Boolean
    get() = this.isSuccess && this.successDataOrThrow.all { it.product != null }

val RequestState<List<CartItemWithProductModel>>.cartSumPrice: Double?
    get() {
        if (!this.isFullyLoaded) return null
        return this.successDataOrThrow.sumOf { it.totalPrice!! }
    }

fun RequestState<List<CartItemWithProductModel>>.cartSumPriceFormatted(locale: Locale): String? =
    cartSumPrice?.let {
        return String.format(locale = locale, "%.2f", it)
    }

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
    data object OnRefresh : CartAction
}

