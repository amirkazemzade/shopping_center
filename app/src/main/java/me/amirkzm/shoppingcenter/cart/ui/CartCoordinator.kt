package me.amirkzm.shoppingcenter.cart.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.generated.destinations.ProductItemDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class CartCoordinator(
    val viewModel: CartViewModel,
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: CartAction, navigator: DestinationsNavigator) {
        when (action) {
            is CartAction.OnItemClick -> {
                navigator.navigate(ProductItemDestination(productId = action.id))
            }

            is CartAction.OnIncreaseItemQuantity -> viewModel.increaseItemQuantity(action.id)
            is CartAction.OnDecreaseItemQuantity -> viewModel.decreaseItemQuantity(action.id)
            is CartAction.OnRemoveItem -> viewModel.removeItem(action.id)
            CartAction.OnCheckOut -> {
                // TODO: Checkout
            }

            CartAction.OnRefresh -> viewModel.onRefresh()
        }
    }
}

@Composable
fun rememberCartCoordinator(
    viewModel: CartViewModel = hiltViewModel(),
): CartCoordinator {
    return remember(viewModel) {
        CartCoordinator(
            viewModel = viewModel
        )
    }
}