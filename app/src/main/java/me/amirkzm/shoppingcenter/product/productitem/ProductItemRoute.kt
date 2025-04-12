package me.amirkzm.shoppingcenter.product.productitem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


@Composable
fun ProductItemRoute(
    coordinator: ProductItemCoordinator = rememberProductItemCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ProductItemState())

    // UI Actions
    val actionsHandler: (ProductItemAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    ProductItemScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


