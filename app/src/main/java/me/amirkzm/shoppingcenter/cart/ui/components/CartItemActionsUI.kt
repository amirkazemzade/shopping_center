package me.amirkzm.shoppingcenter.cart.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CartItemActionsUI(
    cartItem: CartItemWithProductModel,
    onIncreaseQuantity: (id: Int) -> Unit,
    onDecreaseQuantity: (id: Int) -> Unit,
    onRemoveItem: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val enabled = cartItem.product != null

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
            .shimmer(enabled = !enabled)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            IconButton(
                enabled = enabled,
                onClick = {
                    onIncreaseQuantity(cartItem.product!!.id)
                },
                colors = IconButtonDefaults.filledTonalIconButtonColors(),
                shape = IconButtonDefaults.extraSmallSquareShape,
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_add_24),
                    contentDescription = "Increase Quantity",
                )
            }

            Heights.Smallest()
            Text(text = cartItem.quantity.toString())
            Heights.Smallest()
            IconButton(
                enabled = enabled,
                onClick = {
                    if (cartItem.quantity == 1) onRemoveItem(cartItem.product!!.id)
                    else onDecreaseQuantity(cartItem.product!!.id)
                },
                shape = IconButtonDefaults.extraSmallSquareShape,
                colors = IconButtonDefaults.filledTonalIconButtonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
            ) {
                if (cartItem.quantity == 1) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = "Remove From Cart",
                    )
                } else {
                    Icon(
                        painterResource(R.drawable.remove_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                        contentDescription = "Decrease Quantity",
                    )
                }
            }

        }
    }
}
