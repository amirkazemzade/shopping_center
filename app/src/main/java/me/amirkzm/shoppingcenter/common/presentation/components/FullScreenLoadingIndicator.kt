package me.amirkzm.shoppingcenter.common.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.common.presentation.theme.ShoppingCenterTheme

@Composable
fun FullScreenLoadingIndicator(
    modifier: Modifier = Modifier,
    message: String? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
        if (message != null) {
            Heights.Medium()
            MessageTextWithTrailingLoading(message)
        }
    }
}

@Preview
@Composable
fun FullScreenLoadingIndicatorPreview() {
    ShoppingCenterTheme {
        Surface {
            FullScreenLoadingIndicator(
                message = "This is a process message"
            )
        }
    }
}