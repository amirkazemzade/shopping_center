package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProductsListCoordinator(
    val viewModel: ProductsListViewModel,
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: ProductsListAction) {
        when (action) {
            ProductsListAction.FetchProductsList ->
                viewModel.getProductsList()

            ProductsListAction.OnClick -> TODO()
        }
    }


}

@Composable
fun rememberProductsListCoordinator(
    viewModel: ProductsListViewModel = hiltViewModel(),
): ProductsListCoordinator {
    return remember(viewModel) {
        ProductsListCoordinator(
            viewModel = viewModel
        )
    }
}