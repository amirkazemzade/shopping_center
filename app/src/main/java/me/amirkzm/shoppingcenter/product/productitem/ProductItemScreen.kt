package me.amirkzm.shoppingcenter.product.productitem

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenErrorMessage
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenLoadingIndicator
import me.amirkzm.shoppingcenter.product.productitem.components.ProductItemView

@Composable
fun ProductItemScreen(
    productId: Int,
    state: ProductItemState,
    onAction: (ProductItemAction) -> Unit,
) {
    Surface {
        LaunchedEffect(state) {
            if (state is RequestState.Idle) {
                onAction(ProductItemAction.FetchProduct(id = productId))
            }
        }

        when (state) {
            RequestState.Idle -> FullScreenLoadingIndicator(
                message = "Initializing"
            )

            RequestState.Loading -> FullScreenLoadingIndicator(
                message = "Loading Product"
            )

            is RequestResource.Error -> FullScreenErrorMessage(
                message = state.error.message,
                onRetry = { onAction(ProductItemAction.FetchProduct(id = productId)) }
            )

            is RequestResource.Success -> ProductItemView(
                product = state.data,
                onNavigateBack = { onAction(ProductItemAction.NavigateBack) },
                onAddToCart = {
                    // TODO: Add to cart
                },
            )
        }
    }
}

@Composable
@Preview(name = "ProductItem")
private fun ProductItemScreenPreview() {
    ProductItemScreen(
        productId = 1,
        state = RequestState.Idle,
        onAction = {}
    )
}

