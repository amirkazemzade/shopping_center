package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
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
    Scaffold {
        Box(
            modifier = Modifier.padding(it)
        ) {
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

                is RequestResource.Success -> ProductsListView(
                    productsList = state.data,
                    onItemClick = { product ->
                        onAction(
                            ProductsListAction.OnProductItemClick(
                                product.id
                            )
                        )
                    }
                )
            }
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

