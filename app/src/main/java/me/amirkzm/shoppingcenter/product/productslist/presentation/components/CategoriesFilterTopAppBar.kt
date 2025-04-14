package me.amirkzm.shoppingcenter.product.productslist.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.product.common.domain.models.Category

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CategoriesFilterTopAppBar(
    categoriesListState: LazyListState,
    categories: List<Category>,
    selectedCategory: Category?,
    onDeselectCategory: () -> Unit,
    onSelectCategory: (Category) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            CategoriesFilterRow(
                listState = categoriesListState,
                categories = categories,
                selectedCategory = selectedCategory,
                onDeselectCategory = onDeselectCategory,
                onSelectCategory = onSelectCategory,
                modifier = Modifier.padding(end = if (selectedCategory == null) 16.dp else 0.dp)
            )
        },
        actions = {
            if (selectedCategory != null) {
                IconButton(onClick = onDeselectCategory) {
                    Icon(
                        painter = painterResource(R.drawable.round_close_24),
                        contentDescription = "Deselect Category"
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}
