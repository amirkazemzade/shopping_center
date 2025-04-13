package me.amirkzm.shoppingcenter.product.productitem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.common.presentation.theme.Widths
import me.amirkzm.shoppingcenter.common.presentation.theme.goldYellow
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating
import me.amirkzm.shoppingcenter.product.common.presentation.components.ProductImage

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductItemView(
    product: ProductItemModel,
    onNavigateBack: () -> Unit,
    onAddToCart: (product: ProductItemModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateBack()
                        },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back to previous screen",
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("${product.price} $", style = MaterialTheme.typography.headlineSmall)
                    Button(
                        onClick = {
                            onAddToCart(product)
                        },
                    ) { Text("Add to cart") }
                }
            }
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            ProductImage(product)
            Heights.Smallest()
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = product.title, style = MaterialTheme.typography.headlineSmall)
                Heights.Small()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        tint = goldYellow,
                        contentDescription = "Star Rating Icon",
                    )
                    Widths.Smallest()
                    Text(
                        text = "${product.rating.rate} (${product.rating.count})",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Heights.Medium()
                Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
                Heights.Small()
                ElevatedSuggestionChip(
                    label = { Text(product.category) },
                    onClick = {
                        // TODO("Navigate to categoy items list")
                    },
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun PreviewProductItemView() {
    PreviewTheme {
        Surface {
            ProductItemView(
                ProductItemModel(
                    id = 1,
                    title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    price = 109.95,
                    description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                    category = "men's clothing",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        count = 120, rate = 3.9
                    )
                ),
                onNavigateBack = { },
                onAddToCart = { },
            )
        }
    }
}
