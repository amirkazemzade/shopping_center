package me.amirkzm.shoppingcenter.product.productitem

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductItemScreen(
    state: ProductItemState,
    onAction: (ProductItemAction) -> Unit,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "ProductItem")
private fun ProductItemScreenPreview() {
    ProductItemScreen(
        state = ProductItemState(),
        onAction = {}
    )
}

