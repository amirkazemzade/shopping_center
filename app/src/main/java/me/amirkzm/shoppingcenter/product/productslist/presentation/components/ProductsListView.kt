package me.amirkzm.shoppingcenter.product.productslist.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating
import kotlin.math.ceil

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductsListView(
    productsList: ProductsListModel,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = modifier,
        ) {
            val itemSize = 200.dp
            val columnCount = (this@BoxWithConstraints.maxWidth / itemSize).toInt()
            val rowCount = ceil(productsList.size.toDouble() / columnCount).toInt()
            items(
                count = rowCount
            ) { rowIndex ->
                Row{
                    repeat(columnCount) { columnIndex ->
                        val itemIndex = (rowIndex * columnCount) + columnIndex
                        if (itemIndex >= productsList.size) return@repeat
                        ProductsListItem(
                            productItem = productsList[itemIndex],
                            modifier = Modifier
                                .fillMaxWidth(1.0f / (columnCount - columnIndex))
                                .padding(8.dp)
                        )
                    }
                }
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
            } as ProductsListModel,
        )
        
    }
}