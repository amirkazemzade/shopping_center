package me.amirkzm.shoppingcenter.cart.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkzm.shoppingcenter.cart.ui.components.CartBottomBar
import me.amirkzm.shoppingcenter.cart.ui.components.CartItemsListView
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    state: CartState,
    onAction: (CartAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cart") })
        },
        bottomBar = {
            CartBottomBar(
                state,
                onCheckout = { onAction(CartAction.OnCheckOut) }
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            if (state.items.isEmpty()) {
                CartEmptyView()
            } else {
                CartItemsListView(
                    items = state.items,
                    onIncreaseItemQuantity = { id ->
                        onAction(CartAction.OnItemClick(id))
                    },
                    onDecreaseItemQuantity = { id ->
                        onAction(CartAction.OnItemClick(id))
                    },
                    onRemoveItem = { id ->
                        onAction(CartAction.OnRemoveItem(id))
                    },
                    onItemClick = { id ->
                        onAction(CartAction.OnItemClick(id))
                    }
                )
            }
        }
    }
}

@Composable
fun CartEmptyView(
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            "Cart is empty!\nAdd some items to your cart.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
}


@Composable
@PreviewLightDark
private fun CartScreenPreview() {
    PreviewTheme {
        Surface {
            CartScreen(
                state = CartState(
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
                ),
                onAction = {}
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun CartScreenEmptyPreview() {
    PreviewTheme {
        Surface {
            CartScreen(
                state = CartState(),
                onAction = {}
            )
        }
    }
}

