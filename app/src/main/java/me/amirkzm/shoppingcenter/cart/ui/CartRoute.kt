package me.amirkzm.shoppingcenter.cart.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph


@Destination<RootGraph>
@Composable
fun CartRoute(
    coordinator: CartCoordinator = rememberCartCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(CartState())

    // UI Actions
    val actionsHandler: (CartAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CartScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


