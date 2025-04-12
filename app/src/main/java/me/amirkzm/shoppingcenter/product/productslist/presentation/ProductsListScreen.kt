package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenErrorMessage
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenLoadingIndicator
import me.amirkzm.shoppingcenter.product.productslist.presentation.components.ProductsListView


@Composable
fun ProductsListScreen(
    state: ProductsListState,
    onAction: (ProductsListAction) -> Unit,
) {
    Surface {
        LaunchedEffect(state) {
            if (state is RequestState.Idle) {
                onAction(ProductsListAction.FetchProductsList)
            }
        }

        when (state) {
            RequestState.Idle -> FullScreenLoadingIndicator(
                message = "Initializing"
            )

            RequestState.Loading -> FullScreenLoadingIndicator(
                message = "Loading"
            )

            is RequestResource.Error -> FullScreenErrorMessage(
                message = state.error.message,
                onRetry = { onAction(ProductsListAction.FetchProductsList) }
            )

            is RequestResource.Success -> ProductsListView(productsList = state.data)
        }
    }
}

@Composable
@Preview(name = "ProductsList")
private fun ProductsListScreenPreview() {
    ProductsListScreen(
        state = RequestState.Idle,
        onAction = {}
    )
}

