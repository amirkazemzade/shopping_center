package me.amirkzm.shoppingcenter.cart.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.cart.domain.models.mocks.mockCartItemWithNullProductModelList
import me.amirkzm.shoppingcenter.cart.domain.models.mocks.mockCartItemWithProductModelList
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItemsListView(
    items: List<CartItemWithProductModel>,
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
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CartItemsListViewPreview() {
    PreviewTheme {
        CartItemsListView(
            items = mockCartItemWithProductModelList
        )
    }
}

@PreviewLightDark
@Composable
private fun CartItemsListViewFullLoadingPreview() {
    PreviewTheme {
        CartItemsListView(
            items = mockCartItemWithNullProductModelList
        )
    }
}

@PreviewLightDark
@Composable
private fun CartItemsListViewPartialLoadingPreview() {
    PreviewTheme {
        CartItemsListView(
            items = (mockCartItemWithNullProductModelList + mockCartItemWithProductModelList).shuffled()
        )
    }
}