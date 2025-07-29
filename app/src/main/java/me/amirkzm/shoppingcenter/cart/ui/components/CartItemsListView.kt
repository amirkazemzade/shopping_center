package me.amirkzm.shoppingcenter.cart.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import me.amirkzm.shoppingcenter.cart.ui.CartItemState
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItemsListView(
    items: List<CartItemState>,
    modifier: Modifier = Modifier,
    onIncreaseItemQuantity: (id: Int) -> Unit = {},
    onDecreaseItemQuantity: (id: Int) -> Unit = {},
    onRemoveItem: (id: Int) -> Unit = {},
    onItemClick: (id: Int) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(items) { cartItem ->
            CartItemView(
                cartItem = cartItem,
                onIncreaseQuantity = onIncreaseItemQuantity,
                onDecreaseQuantity = onDecreaseItemQuantity,
                onRemoveItem = onRemoveItem,
                onItemClick = onItemClick,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CartItemsListViewPreview() {
    PreviewTheme {
        CartItemsListView(
            items = listOf(
                CartItemState(
                    productItemModel = ProductItemModel(
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
                ),
                CartItemState(
                    productItemModel = ProductItemModel(
                        id = 2,
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
                ),
                CartItemState(
                    productItemModel = ProductItemModel(
                        id = 3,
                        title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                        price = 109.95,
                        description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                        category = "men's clothing",
                        image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                        rating = Rating(
                            count = 120, rate = 3.9
                        )
                    ),
                    quantity = 3,
                )
            )
        )
    }
}