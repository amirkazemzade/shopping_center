package me.amirkzm.shoppingcenter.cart.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.common.presentation.theme.Widths
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating
import me.amirkzm.shoppingcenter.product.common.presentation.components.ProductImage

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CartItemView(
    cartItem: CartItemWithProductModel,
    modifier: Modifier = Modifier,
    onDecreaseQuantity: (id: Int) -> Unit = {},
    onIncreaseQuantity: (id: Int) -> Unit = {},
    onRemoveItem: (id: Int) -> Unit = {},
    onItemClick: (id: Int) -> Unit = {},
) {
    Card(
        onClick = {
            if (cartItem.product == null) return@Card
            onItemClick(cartItem.product.id)
        },
        modifier = modifier
    ) {
        val isProductLoading = cartItem.product == null
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)
        ) {
            Card {
                ProductImage(
                    cartItem.product,
                    modifier = Modifier
                        .size(120.dp)
                        .shimmer(enabled = isProductLoading)
                )
            }
            Widths.ExtraSmall()
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cartItem.product?.title ?: "Product is loading",
                    modifier = Modifier
                        .shimmer(enabled = isProductLoading)
                )
                Heights.ExtraLarge()
                val priceFormat =
                    if (cartItem.totalPrice != null) "%.2f".format(cartItem.totalPrice)
                    else "--.--"
                Text(
                    text = "$priceFormat$",
                    style = MaterialTheme.typography.titleLargeEmphasized.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shimmer(enabled = isProductLoading)
                )
            }
            Widths.ExtraSmall()
            CartItemActionsUI(
                cartItem = cartItem,
                onIncreaseQuantity = onIncreaseQuantity,
                onDecreaseQuantity = onDecreaseQuantity,
                onRemoveItem = onRemoveItem,
            )
        }
    }
}


@Composable
fun Modifier.shimmer(
    enabled: Boolean,
): Modifier = if (enabled) then(this.shimmer()) else this

@PreviewLightDark
@Composable
private fun CartItemEmptyViewPreview() {
    PreviewTheme {
        CartItemView(
            cartItem = CartItemWithProductModel(
                product = null,
                quantity = 1,
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun CartItemViewPreview() {
    PreviewTheme {
        CartItemView(
            cartItem = CartItemWithProductModel(
                product = ProductItemModel(
                    id = 1,
                    title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    price = 109.95,
                    description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                    category = "men's clothing",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        count = 120, rate = 3.9
                    )
                ),
                quantity = 1,
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun CartItemViewHighQuantityPreview() {
    PreviewTheme {
        CartItemView(
            cartItem = CartItemWithProductModel(
                product = ProductItemModel(
                    id = 1,
                    title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    price = 109.95,
                    description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                    category = "men's clothing",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        count = 120, rate = 3.9
                    )
                ),
                quantity = 5,
            )
        )
    }
}