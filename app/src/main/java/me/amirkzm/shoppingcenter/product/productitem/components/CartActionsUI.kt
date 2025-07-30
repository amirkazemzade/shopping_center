package me.amirkzm.shoppingcenter.product.productitem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.common.presentation.theme.Widths

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun CartActionsUI(
    productId: Int,
    cartItem: CartItemModel,
    isIncreaseQuantityLoading: Boolean,
    isDecreaseQuantityLoading: Boolean,
    isRemoveFromCartLoading: Boolean,
    onIncreaseQuantity: (id: Int) -> Unit,
    onDecreaseQuantity: (id: Int) -> Unit,
    onRemoveItem: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isActionsEnabled =
        !isIncreaseQuantityLoading && !isDecreaseQuantityLoading && !isRemoveFromCartLoading
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DecreaseOrRemoveActionButton(
                productId = productId,
                cartItem = cartItem,
                onRemoveItem = onRemoveItem,
                onDecreaseQuantity = onDecreaseQuantity,
                isActionsEnabled = isActionsEnabled,
                isRemoveFromCartLoading = isRemoveFromCartLoading,
                isDecreaseQuantityLoading = isDecreaseQuantityLoading
            )
            Widths.Small()
            Text(
                text = cartItem.quantity.toString(),
                style = MaterialTheme.typography.headlineSmall
            )
            Widths.Small()
            IncreaseActionButton(
                productId = productId,
                isActionsEnabled = isActionsEnabled,
                isIncreaseQuantityLoading = isIncreaseQuantityLoading,
                onIncreaseQuantity = onIncreaseQuantity,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private fun IncreaseActionButton(
    productId: Int,
    isActionsEnabled: Boolean,
    isIncreaseQuantityLoading: Boolean,
    onIncreaseQuantity: (Int) -> Unit,
) {
    IconButton(
        enabled = isActionsEnabled,
        onClick = { onIncreaseQuantity(productId) },
        colors = IconButtonDefaults.filledTonalIconButtonColors(),
        shape = IconButtonDefaults.extraSmallSquareShape,
    ) {
        if (isIncreaseQuantityLoading) {
            LoadingIndicator(color = IconButtonDefaults.filledTonalIconButtonColors().contentColor)
        } else {
            Icon(
                painter = painterResource(R.drawable.round_add_24),
                contentDescription = "Increase Quantity",
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private fun DecreaseOrRemoveActionButton(
    productId: Int,
    cartItem: CartItemModel,
    isActionsEnabled: Boolean,
    isRemoveFromCartLoading: Boolean,
    isDecreaseQuantityLoading: Boolean,
    onRemoveItem: (Int) -> Unit,
    onDecreaseQuantity: (Int) -> Unit,
) {
    IconButton(
        enabled = isActionsEnabled,
        onClick = {
            if (cartItem.quantity == 1) onRemoveItem(productId)
            else onDecreaseQuantity(productId)
        },
        shape = IconButtonDefaults.extraSmallSquareShape,
        colors = IconButtonDefaults.filledTonalIconButtonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
    ) {
        when {
            isRemoveFromCartLoading || isDecreaseQuantityLoading -> LoadingIndicator(
                color = MaterialTheme.colorScheme.onErrorContainer
            )

            cartItem.quantity == 1 -> {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "Remove From Cart",
                )
            }

            else -> {
                Icon(
                    painterResource(R.drawable.remove_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = "Decrease Quantity",
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CartActionsUiIncreaseLoadingPreview() {
    PreviewTheme {
        CartActionsUI(
            productId = 1,
            cartItem = CartItemModel(
                id = 1,
                quantity = 1
            ),
            isIncreaseQuantityLoading = true,
            isDecreaseQuantityLoading = false,
            isRemoveFromCartLoading = false,
            onIncreaseQuantity = {},
            onDecreaseQuantity = {},
            onRemoveItem = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun CartActionsUiDecreaseLoadingPreview() {
    PreviewTheme {
        CartActionsUI(
            productId = 1,
            cartItem = CartItemModel(
                id = 1,
                quantity = 1
            ),
            isIncreaseQuantityLoading = false,
            isDecreaseQuantityLoading = true,
            isRemoveFromCartLoading = false,
            onIncreaseQuantity = {},
            onDecreaseQuantity = {},
            onRemoveItem = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun CartActionsUiRemoveLoadingPreview() {
    PreviewTheme {
        CartActionsUI(
            productId = 1,
            cartItem = CartItemModel(
                id = 1,
                quantity = 1
            ),
            isIncreaseQuantityLoading = false,
            isDecreaseQuantityLoading = false,
            isRemoveFromCartLoading = true,
            onIncreaseQuantity = {},
            onDecreaseQuantity = {},
            onRemoveItem = {},
        )
    }
}

