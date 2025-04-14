package me.amirkzm.shoppingcenter.product.productslist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductsListView(
    productsList: ProductsListModel,
    categories: List<Category>,
    onItemClick: (product: ProductItemModel) -> Unit,
    onSelectCategory: (category: Category) -> Unit,
    onDeselectCategory: () -> Unit,
    modifier: Modifier = Modifier,
    selectedCategory: Category? = null,
) {
    val effectiveItemSize = 200.dp

    val categoriesListState = rememberLazyListState()

    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())

    LaunchedEffect(selectedCategory) {
        val indexOfSelectedCategory = categories.indexOfFirst { it == selectedCategory }
        if (indexOfSelectedCategory < 0) return@LaunchedEffect
        categoriesListState.scrollToItem(indexOfSelectedCategory)
    }

    Scaffold(
        topBar = {
            CategoriesFilterTopAppBar(
                categoriesListState = categoriesListState,
                categories = categories,
                selectedCategory = selectedCategory,
                onDeselectCategory = onDeselectCategory,
                onSelectCategory = onSelectCategory,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(effectiveItemSize),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            items(
                items = productsList,
            ) { item ->
                ProductsListItemView(
                    productItem = item,
                    onClick = onItemClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

            }
        }
    }
}


@Preview
@Composable
private fun PreviewProductsList() {
    Surface {
        ProductsListView(
            productsList = buildList {
                repeat(10) {
                    addAll(
                        listOf(
                            ProductItemModel(
                                id = 1,
                                title = "Product 1",
                                price = 10.0,
                                description = "Description 1",
                                category = "Category 1",
                                image = "https://picsum.photos/201",
                                rating = Rating(
                                    count = 1,
                                    rate = 1.0
                                )
                            ),
                            ProductItemModel(
                                id = 2,
                                title = "Product 2",
                                price = 20.0,
                                description = "Description 2",
                                category = "Category 2",
                                image = "https://picsum.photos/150",
                                rating = Rating(
                                    count = 2,
                                    rate = 2.0
                                )
                            ),
                            ProductItemModel(
                                id = 3,
                                title = "Product 3",
                                price = 30.0,
                                description = "Description 3",
                                category = "Category 3",
                                image = "https://picsum.photos/300",
                                rating = Rating(
                                    count = 3,
                                    rate = 3.0
                                )
                            ),
                            ProductItemModel(
                                id = 4,
                                title = "Product 4",
                                price = 30.0,
                                description = "Description 4",
                                category = "Category 3",
                                image = "https://picsum.photos/204",
                                rating = Rating(
                                    count = 3,
                                    rate = 3.0
                                )
                            ),
                            ProductItemModel(
                                id = 5,
                                title = "Product 5",
                                price = 30.0,
                                description = "Description 5",
                                category = "Category 3",
                                image = "https://picsum.photos/305",
                                rating = Rating(
                                    count = 3,
                                    rate = 3.0
                                )
                            ),
                        )

                    )
                }
            },
            onItemClick = {},
            onSelectCategory = {},
            onDeselectCategory = {},
            categories = listOf(
                "Category 1",
                "Category 2",
                "Category 3",
                "Category 4",
                "Category 5",
            ),
            selectedCategory = "Category 4"
        )
    }
}