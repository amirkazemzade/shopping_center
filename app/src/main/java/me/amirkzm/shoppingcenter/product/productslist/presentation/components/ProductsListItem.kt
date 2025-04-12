package me.amirkzm.shoppingcenter.product.productslist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductsListItem(
    productItem: ProductItemModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                GlideImage(
                    model = productItem.image,
                    contentDescription = productItem.description,
                    // Note: Do Not use `painterResource(R.drawable.ID)` as it might cause scrolling
                    // crash when combining lazy column with glide.
                    loading = placeholder(resourceId = R.drawable.shopping_basket),
                    failure = placeholder(resourceId = R.drawable.shopping_basket),
                )
            }
            Heights.ExtraSmall(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = productItem.title,
                    style = MaterialTheme.typography.bodyMedium
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
