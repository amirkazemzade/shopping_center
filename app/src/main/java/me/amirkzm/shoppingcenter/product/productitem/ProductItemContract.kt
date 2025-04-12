package me.amirkzm.shoppingcenter.product.productitem


/**
 * UI State that represents ProductItemScreen
 **/
class ProductItemState

/**
 * ProductItem Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProductItemAction {
    data object OnClick : ProductItemAction
}

