package me.amirkzm.shoppingcenter.product.productitem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.generated.destinations.ProductsListDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProductItemCoordinator(
    val viewModel: ProductItemViewModel,
) {
    val productStateFlow = viewModel.productStateFlow
    val cartStateFlow = viewModel.cartStateFlow
    val addToCartState = viewModel.addToCartState
    val increaseQuantityState = viewModel.increaseQuantityState
    val decreaseQuantityState = viewModel.decreaseQuantityState
    val removeFromCartState = viewModel.removeFromCartState

    fun handle(action: ProductItemAction, navigator: DestinationsNavigator) {
        when (action) {
            is ProductItemAction.FetchProduct -> viewModel.fetchProduct(id = action.id)

            ProductItemAction.NavigateBack -> navigator.navigateUp()

            is ProductItemAction.OnClickAddToCart -> {
                viewModel.addToCart(productId = action.id)
            }

            is ProductItemAction.OnClickCategory -> navigator.navigate(
                ProductsListDestination(
                    startingCategory = action.category,
                )
            )

            is ProductItemAction.OnIncreaseQuantityInCart -> viewModel.increaseQuantityInCart(
                productId = action.id,
            )

            is ProductItemAction.OnDecreaseQuantityInCart -> viewModel.decreaseQuantityInCart(
                productId = action.id,
            )

            is ProductItemAction.OnRemoveFromCart -> viewModel.removeFromCart(
                productId = action.id,
            )

        }
    }


}

@Composable
fun rememberProductItemCoordinator(
    viewModel: ProductItemViewModel = hiltViewModel(),
): ProductItemCoordinator {
    return remember(viewModel) {
        ProductItemCoordinator(
            viewModel = viewModel
        )
    }
}