package me.amirkzm.shoppingcenter.cart.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.cart.domain.models.mocks.mockCartItemWithProductModelList
import me.amirkzm.shoppingcenter.cart.ui.components.CartBottomBar
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenErrorMessage
import me.amirkzm.shoppingcenter.common.presentation.components.FullScreenLoadingIndicator
import me.amirkzm.shoppingcenter.common.presentation.theme.PreviewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    state: RequestState<List<CartItemWithProductModel>>,
    onAction: (CartAction) -> Unit,
    contentWindowInsets: WindowInsets = WindowInsets.systemBars,
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            CartBottomBar(
                state = state,
                onCheckout = { onAction(CartAction.OnCheckOut) }
            )
        },
        contentWindowInsets = contentWindowInsets,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                when (state) {
                    RequestState.Idle -> FullScreenLoadingIndicator(
                        message = "Initializing"
                    )

                    RequestState.Loading -> FullScreenLoadingIndicator(
                        message = "Loading"
                    )

                    is RequestResource.Error -> FullScreenErrorMessage(
                        message = state.error.message,
                        onRetry = { onAction(CartAction.OnRefresh) }
                    )

                    is RequestResource.Success -> CartView(state.data, onAction = onAction)
                }
            }
        }
    }
}


@Composable
fun CartEmptyView(
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            "Cart is empty!\nAdd some items to your cart.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
}

@PreviewLightDark
@Composable
private fun CartScreenPreview() {
    PreviewTheme {
        CartScreen(
            state = RequestResource.Success(
                mockCartItemWithProductModelList
            ),
            onAction = {},
        )
    }
}