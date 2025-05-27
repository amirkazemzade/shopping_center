package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenErrorMessage
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenLoadingIndicator
import me.amirkzm.shoppingcenter.product.productslist.presentation.components.ProductsListView


@Composable
fun ProductsListScreen(
    productsState: ProductsListState,
    categoriesState: CategoriesState,
    selectedCategoryState: SelectedCategoryState,
    onAction: (ProductsListAction) -> Unit,
    showBackNavigation: Boolean = false,
) {
    Surface {
        LaunchedEffect(productsState) {
            if (productsState is RequestState.Idle) {
                onAction(ProductsListAction.FetchProductsList)
            }
        }

        LaunchedEffect(categoriesState) {
            if (categoriesState is RequestState.Idle) {
                onAction(ProductsListAction.FetchCategories)
            }
        }

        when (productsState) {
            RequestState.Idle -> FullScreenLoadingIndicator(
                message = "Initializing"
            )

            RequestState.Loading -> FullScreenLoadingIndicator(
                message = "Loading"
            )

            is RequestResource.Error -> FullScreenErrorMessage(
                message = productsState.error.message,
                onRetry = { onAction(ProductsListAction.FetchProductsList) }
            )

            // TODO: State handling for categories
            is RequestResource.Success -> ProductsListView(
                productsList = productsState.data,
                categories = if (categoriesState is RequestResource.Success) categoriesState.data else emptyList(),
                onItemClick = { product ->
                    onAction(
                        ProductsListAction.OnProductItemClick(
                            product.id
                        )
                    )
                },
                onSelectCategory = { category ->
                    onAction(ProductsListAction.SelectCategory(category))
                },
                onDeselectCategory = { onAction(ProductsListAction.DeselectCategory) },
                onNavigateUp = { onAction(ProductsListAction.NavigateBack) },
                selectedCategory = selectedCategoryState,
                showBackNavigation = showBackNavigation,
            )
        }
    }
}
