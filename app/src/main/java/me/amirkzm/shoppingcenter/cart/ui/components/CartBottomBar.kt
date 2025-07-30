package me.amirkzm.shoppingcenter.cart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.cart.ui.cartSumPriceFormatted
import me.amirkzm.shoppingcenter.cart.ui.isFullyLoaded
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrNull
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.Rating

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBottomBar(
    state: RequestState<List<CartItemWithProductModel>>,
    onCheckout: () -> Unit = {},
) {
    val locale = Locale.current
    BottomAppBar(
        windowInsets = BottomAppBarDefaults.windowInsets
            .exclude(WindowInsets.navigationBars)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "${state.cartSumPriceFormatted(locale = locale.platformLocale) ?: "--.--"} $",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                enabled = state.isFullyLoaded && state.successDataOrNull?.isEmpty() != true,
                onClick = { onCheckout() },
            ) { Text("Checkout") }
        }
    }
}

@PreviewLightDark
@Composable
private fun CartBottomBarLoadingPreview() {
    PreviewTheme {
        CartBottomBar(
            state = RequestState.Loading,
        )
    }
}

@PreviewLightDark
@Composable
private fun CartBottomBarEmptySuccessPreview() {
    PreviewTheme {
        CartBottomBar(
            state = RequestResource.Success(emptyList()),
        )
    }
}

@PreviewLightDark
@Composable
private fun CartBottomBarSuccessPreview() {
    PreviewTheme {
        CartBottomBar(
            state = RequestResource.Success(
                listOf(
                    CartItemWithProductModel(
                        product = ProductItemModel(
                            id = 1,
                            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                            price = 109.95,
                            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to",
                            category = "men's clothing",
                            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                            rating = Rating(
                                rate = 3.9,
                                count = 120
                            )
                        ),
                        quantity = 1
                    )
                )
            ),
        )
    }
}
