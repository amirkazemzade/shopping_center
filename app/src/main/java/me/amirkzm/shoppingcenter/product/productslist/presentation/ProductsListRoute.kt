package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph


@Destination<RootGraph>(start = true)
@Composable
fun ProductsListRoute(
    coordinator: ProductsListCoordinator = rememberProductsListCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actionsHandler: (ProductsListAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    ProductsListScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


