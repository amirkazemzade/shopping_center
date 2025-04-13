package me.amirkzm.shoppingcenter.product.productslist.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.presentation.components.ProductImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductsListItemView(
    productItem: ProductItemModel,
    onClick: (product: ProductItemModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onClick(productItem) },
        modifier = modifier
    ) {
        Column {
            ProductImage(productItem)
            Heights.ExtraSmall()
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = productItem.title,
                    style = MaterialTheme.typography.bodyMedium,
                    minLines = 3,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${productItem.price} $",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
