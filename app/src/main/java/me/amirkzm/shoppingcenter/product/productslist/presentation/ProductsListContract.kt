package me.amirkzm.shoppingcenter.product.productslist.presentation

import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel


/**
 * UI State that represents ProductsListScreen
 **/
typealias ProductsListState = RequestState<ProductsListModel>

/**
 * ProductsList Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProductsListAction {
    data object FetchProductsList : ProductsListAction
    data object OnClick: ProductsListAction
}

