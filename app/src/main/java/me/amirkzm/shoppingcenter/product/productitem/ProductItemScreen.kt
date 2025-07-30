package me.amirkzm.shoppingcenter.product.productitem

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrNull
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenErrorMessage
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenLoadingIndicator
import me.amirkzm.shoppingcenter.product.productitem.components.ProductItemView

@Composable
fun ProductItemScreen(
    productId: Int,
    state: ProductItemState,
    cartState: RequestState<CartItemModel?>,
    addCartState: RequestState<Unit> = RequestState.Idle,
    increaseQuantityState: RequestState<Unit> = RequestState.Idle,
    decreaseQuantityState: RequestState<Unit> = RequestState.Idle,
    removeFromCartState: RequestState<Unit> = RequestState.Idle,
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
                cartItem = cartState.successDataOrNull,
                isAddToCartLoading = addCartState is RequestState.Loading,
                isIncreaseQuantityLoading = increaseQuantityState is RequestState.Loading,
                isDecreaseQuantityLoading = decreaseQuantityState is RequestState.Loading,
                isRemoveFromCartLoading = removeFromCartState is RequestState.Loading,
                onNavigateBack = { onAction(ProductItemAction.NavigateBack) },
                onAddToCart = { product ->
                    onAction(ProductItemAction.OnClickAddToCart(id = productId))
                },
                onIncreaseQuantity = { product ->
                    onAction(ProductItemAction.OnIncreaseQuantityInCart(id = productId))
                },
                onDecreaseQuantity = { product ->
                    onAction(ProductItemAction.OnDecreaseQuantityInCart(id = productId))
                },
                onRemoveItem = { product ->
                    onAction(ProductItemAction.OnRemoveFromCart(id = productId))
                },
                onCategoryClick = { category ->
                    onAction(ProductItemAction.OnClickCategory(category = category))
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
        cartState = RequestState.Idle,
        onAction = {},
    )
}

