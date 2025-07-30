package me.amirkzm.shoppingcenter.product.productitem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import me.amirkzm.shoppingcenter.R
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating

@OptIn(
    ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
fun ProductItemView(
    product: ProductItemModel,
    onNavigateBack: () -> Unit,
    onAddToCart: (id: Int) -> Unit,
    onCategoryClick: (category: String) -> Unit,
    modifier: Modifier = Modifier,
    onIncreaseQuantity: (id: Int) -> Unit = {},
    onDecreaseQuantity: (id: Int) -> Unit = {},
    onRemoveItem: (id: Int) -> Unit = {},
    cartItem: CartItemModel? = null,
    isAddToCartLoading: Boolean = false,
    isIncreaseQuantityLoading: Boolean = false,
    isDecreaseQuantityLoading: Boolean = false,
    isRemoveFromCartLoading: Boolean = false,
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
                            painter = painterResource(R.drawable.round_arrow_back_24),
                            contentDescription = "Back to previous screen",
                        )
                    }
                },
            )
        },
        bottomBar = {
            ProductItemBottomBar(
                product = product,
                cartItem = cartItem,
                isAddToCartLoading = isAddToCartLoading,
                isIncreaseQuantityLoading = isIncreaseQuantityLoading,
                isDecreaseQuantityLoading = isDecreaseQuantityLoading,
                isRemoveFromCartLoading = isRemoveFromCartLoading,
                onAddToCart = onAddToCart,
                onIncreaseQuantity = onIncreaseQuantity,
                onDecreaseQuantity = onDecreaseQuantity,
                onRemoveItem = onRemoveItem,
            )
        },
        modifier = modifier
    ) {
        ProductItemContent(
            product = product,
            onCategoryClick = onCategoryClick,
            modifier = Modifier.padding(it)
        )
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
                onCategoryClick = { },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewProductItemViewWithCartItem() {
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
                onCategoryClick = { },
                cartItem = CartItemModel(
                    id = 1,
                    quantity = 2,
                ),
            )
        }
    }
}
