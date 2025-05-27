package me.amirkzm.shoppingcenter.product.productslist.presentation

import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel


/**
 * UI State that represents ProductsListScreen
 **/
typealias ProductsListState = RequestState<ProductsListModel>

typealias CategoriesState = RequestState<List<Category>>

typealias SelectedCategoryState = Category?

/**
 * ProductsList Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProductsListAction {
    data object FetchProductsList : ProductsListAction
    data class OnProductItemClick(val productId: Int) : ProductsListAction
    data object FetchCategories : ProductsListAction
    data class SelectCategory(val category: String) : ProductsListAction
    data object DeselectCategory : ProductsListAction
    data object NavigateBack : ProductsListAction
}

