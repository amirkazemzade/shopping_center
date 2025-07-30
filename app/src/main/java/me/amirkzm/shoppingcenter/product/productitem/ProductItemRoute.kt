package me.amirkzm.shoppingcenter.product.productitem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>(route = "Product Item")
@Composable
fun ProductItemRoute(
    productId: Int,
    navigator: DestinationsNavigator,
    coordinator: ProductItemCoordinator = rememberProductItemCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.productStateFlow.collectAsStateWithLifecycle()
    val cartState by coordinator.cartStateFlow.collectAsStateWithLifecycle()
    val addToCartState by coordinator.addToCartState.collectAsStateWithLifecycle()
    val increaseQuantityState by coordinator.increaseQuantityState.collectAsStateWithLifecycle()
    val decreaseQuantityState by coordinator.decreaseQuantityState.collectAsStateWithLifecycle()
    val removeFromCartState by coordinator.removeFromCartState.collectAsStateWithLifecycle()

    // UI Actions
    val actionsHandler: (ProductItemAction) -> Unit = { action ->
        coordinator.handle(action, navigator)
    }

    // UI Rendering
    ProductItemScreen(
        productId = productId,
        state = uiState,
        cartState = cartState,
        addCartState = addToCartState,
        increaseQuantityState = increaseQuantityState,
        decreaseQuantityState = decreaseQuantityState,
        removeFromCartState = removeFromCartState,
        onAction = actionsHandler
    )
}


