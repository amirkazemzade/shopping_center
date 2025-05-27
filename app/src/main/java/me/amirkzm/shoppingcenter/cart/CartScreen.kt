package me.amirkzm.shoppingcenter.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartScreen(
    state: CartState,
    onAction: (CartAction) -> Unit,
) {
    Scaffold {
        Text("Cart Screen", modifier = Modifier.padding(it))
    }
}

@Composable
@Preview(name = "Cart")
private fun CartScreenPreview() {
    CartScreen(
        state = CartState(),
        onAction = {}
    )
}

