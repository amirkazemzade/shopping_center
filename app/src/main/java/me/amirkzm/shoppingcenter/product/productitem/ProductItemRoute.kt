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
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actionsHandler: (ProductItemAction) -> Unit = { action ->
        coordinator.handle(action, navigator)
    }

    // UI Rendering
    ProductItemScreen(
        productId = productId,
        state = uiState,
        onAction = actionsHandler
    )
}


