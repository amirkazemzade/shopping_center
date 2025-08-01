package me.amirkzm.shoppingcenter.product.productitem

import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel


/**
 * UI State that represents ProductItemScreen
 **/
typealias ProductItemState = RequestState<ProductItemModel>

/**
 * ProductItem Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProductItemAction {
    data class FetchProduct(val id: Int) : ProductItemAction
    data object NavigateBack : ProductItemAction
    data class OnClickCategory(val category: String) : ProductItemAction
    data class OnClickAddToCart(val id: Int) : ProductItemAction
    data class OnIncreaseQuantityInCart(val id: Int) : ProductItemAction
    data class OnDecreaseQuantityInCart(val id: Int) : ProductItemAction
    data class OnRemoveFromCart(val id: Int) : ProductItemAction
}

