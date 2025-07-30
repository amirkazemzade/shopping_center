package me.amirkzm.shoppingcenter.cart.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.cart.domain.models.mocks.mockCartItemWithProductModelList
import me.amirkzm.shoppingcenter.cart.ui.components.CartItemsListView
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme

@Composable
fun CartView(
    state: List<CartItemWithProductModel>,
    onAction: (CartAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        if (state.isEmpty()) {
            CartEmptyView()
        } else {
            CartItemsListView(
                items = state,
                onIncreaseItemQuantity = { id ->
                    onAction(CartAction.OnIncreaseItemQuantity(id))
                },
                onDecreaseItemQuantity = { id ->
                    onAction(CartAction.OnDecreaseItemQuantity(id))
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


@Composable
@PreviewLightDark
private fun CartViewPreview() {
    PreviewTheme {
        Surface {
            CartView(
                state = mockCartItemWithProductModelList,
                onAction = {}
            )
        }
    }
}


@Composable
@PreviewLightDark
private fun CartViewEmptyPreview() {
    PreviewTheme {
        Surface {
            CartView(
                state = emptyList(),
                onAction = {}
            )
        }
    }
}