package me.amirkzm.shoppingcenter.product.productslist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.product.common.domain.models.Category

@Composable
fun CategoriesFilterRow(
    categories: List<Category>,
    onDeselectCategory: () -> Unit,
    onSelectCategory: (Category) -> Unit,
    modifier: Modifier = Modifier,
    selectedCategory: Category? = null,
    listState: LazyListState = rememberLazyListState(),
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = listState,
        modifier = modifier
    ) {
        items(items = categories) { category ->
            val isSelected = category == selectedCategory
            FilterChip(
                label = { Text(text = category) },
                selected = isSelected,
                onClick = {
                    if (isSelected) onDeselectCategory() else onSelectCategory(
                        category
                    )
                }
            )
        }
    }
}
