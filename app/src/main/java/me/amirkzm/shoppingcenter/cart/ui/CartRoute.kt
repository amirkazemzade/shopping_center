package me.amirkzm.shoppingcenter.cart.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun CartRoute(
    navigator: DestinationsNavigator,
    coordinator: CartCoordinator = rememberCartCoordinator(),
    contentWindowInsets: WindowInsets = WindowInsets.systemBars,
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actionsHandler: (CartAction) -> Unit = { action ->
        coordinator.handle(action, navigator)
    }

    // UI Rendering
    CartScreen(
        state = uiState,
        onAction = actionsHandler,
        contentWindowInsets = contentWindowInsets,
    )
}


