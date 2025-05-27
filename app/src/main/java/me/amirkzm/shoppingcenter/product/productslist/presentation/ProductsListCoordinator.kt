package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.generated.destinations.ProductItemDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.amirkzm.shoppingcenter.product.common.domain.models.Category

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProductsListCoordinator(
    val viewModel: ProductsListViewModel,
) {
    val productsStateFlow = viewModel.stateFlow
    val categoriesStateFlow = viewModel.categoriesFlow
    val selectedCategoryStateFlow = viewModel.selectedCategory
    fun handle(action: ProductsListAction, navigator: DestinationsNavigator) {
        when (action) {
            ProductsListAction.FetchProductsList ->
                viewModel.getProductsList()

            is ProductsListAction.OnProductItemClick -> {
                navigator.navigate(ProductItemDestination(productId = action.productId))
            }

            ProductsListAction.FetchCategories -> viewModel.getCategories()

            is ProductsListAction.SelectCategory -> viewModel.selectCategory(action.category)

            ProductsListAction.DeselectCategory -> viewModel.deselectCategory()
            ProductsListAction.NavigateBack -> navigator.navigateUp()
        }
    }
}

@Composable
fun rememberProductsListCoordinator(
    startingSelectedCategory: Category? = null,
    viewModel: ProductsListViewModel = createProductsListViewModel(startingSelectedCategory),
): ProductsListCoordinator {
    return remember(viewModel) {
        ProductsListCoordinator(
            viewModel = viewModel
        )
    }
}

@Composable
private fun createProductsListViewModel(startingSelectedCategory: Category?): ProductsListViewModel =
    hiltViewModel<ProductsListViewModel, ProductsListViewModel.ProductListViewModelFactory>(
        creationCallback = { factory -> factory.create(startingSelectedCategory) },
    )