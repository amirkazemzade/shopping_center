package me.amirkzm.shoppingcenter.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartScreen(
    state: CartState,
    onAction: (CartAction) -> Unit,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Cart")
private fun CartScreenPreview() {
    CartScreen(
        state = CartState(),
        onAction = {}
    )
}

