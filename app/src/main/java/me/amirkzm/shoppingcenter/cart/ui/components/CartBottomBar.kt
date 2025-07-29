package me.amirkzm.shoppingcenter.cart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.cart.ui.CartState

@Composable
fun CartBottomBar(
    state: CartState,
    onCheckout: () -> Unit = {},
) {
    BottomAppBar {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "${state.items.sumOf { it.totalPrice }} $",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = { onCheckout() },
            ) { Text("Checkout") }
        }
    }
}
