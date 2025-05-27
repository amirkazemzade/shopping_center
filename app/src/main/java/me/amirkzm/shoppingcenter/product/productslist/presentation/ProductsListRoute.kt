package me.amirkzm.shoppingcenter.product.productslist.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.ProductsListDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>(route = "Products List")
@Composable
fun ProductsListRoute(
    navigator: DestinationsNavigator,
    startingCategory: String? = null,
    coordinator: ProductsListCoordinator = rememberProductsListCoordinator(startingSelectedCategory = startingCategory),
) {
    val showBackNavigation = navigator.getBackStackEntry(ProductsListDestination) != null

    // State observing and declarations
    val productsState by coordinator.productsStateFlow.collectAsStateWithLifecycle()
    val categoriesState by coordinator.categoriesStateFlow.collectAsStateWithLifecycle()
    val selectedCategoryState by coordinator.selectedCategoryStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actionsHandler: (ProductsListAction) -> Unit = { action ->
        coordinator.handle(action, navigator)
    }

    // UI Rendering
    ProductsListScreen(
        productsState = productsState,
        categoriesState = categoriesState,
        selectedCategoryState = selectedCategoryState,
        onAction = actionsHandler,
        showBackNavigation = showBackNavigation,
    )
}


