package me.amirkzm.shoppingcenter.product.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductImage(
    product: ProductItemModel?,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f)
            .background(Color.White)
            .padding(4.dp)
    ) {
        GlideImage(
            model = product?.image,
            contentDescription = product?.description,
            // Note: Do Not use `painterResource(R.drawable.ID)` as it might cause scrolling
            // crash when combining lazy column with glide.
            loading = placeholder(resourceId = R.drawable.shopping_basket),
            failure = placeholder(resourceId = R.drawable.shopping_basket),
        )
    }
}
