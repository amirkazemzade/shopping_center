package me.amirkzm.shoppingcenter.product.productitem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun ProductItemBottomBar(
    product: ProductItemModel,
    cartItem: CartItemModel?,
    isAddToCartLoading: Boolean = false,
    isIncreaseQuantityLoading: Boolean = false,
    isDecreaseQuantityLoading: Boolean = false,
    isRemoveFromCartLoading: Boolean = false,
    onAddToCart: (Int) -> Unit,
    onIncreaseQuantity: (id: Int) -> Unit,
    onDecreaseQuantity: (id: Int) -> Unit,
    onRemoveItem: (id: Int) -> Unit,
) {
    BottomAppBar {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("${product.price} $", style = MaterialTheme.typography.headlineSmall)
            if (cartItem != null) {
                CartActionsUI(
                    productId = product.id,
                    cartItem = cartItem,
                    isIncreaseQuantityLoading = isIncreaseQuantityLoading,
                    isDecreaseQuantityLoading = isDecreaseQuantityLoading,
                    isRemoveFromCartLoading = isRemoveFromCartLoading,
                    onIncreaseQuantity = onIncreaseQuantity,
                    onDecreaseQuantity = onDecreaseQuantity,
                    onRemoveItem = onRemoveItem,
                )
            } else {
                AddToCartButton(
                    product = product,
                    isAddToCartLoading = isAddToCartLoading,
                    onAddToCart = onAddToCart,
                )
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private fun AddToCartButton(
    product: ProductItemModel,
    isAddToCartLoading: Boolean,
    onAddToCart: (id: Int) -> Unit,
) {
    Button(
        enabled = !isAddToCartLoading,
        onClick = {
            onAddToCart(product.id)
        },
    ) {
        if (!isAddToCartLoading)
            Text("Add to cart")
        else
            ContainedLoadingIndicator()
    }
}