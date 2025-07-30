package me.amirkzm.shoppingcenter.product.productitem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.common.presentation.theme.Widths
import me.amirkzm.shoppingcenter.common.presentation.theme.goldYellow
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.presentation.components.ProductImage

@Composable
fun ProductItemContent(
    product: ProductItemModel,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
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
                    painter = painterResource(R.drawable.round_star_24),
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
                onClick = { onCategoryClick(product.category) },
            )
        }
    }
}
