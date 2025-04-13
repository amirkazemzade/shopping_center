package me.amirkzm.shoppingcenter.common.presentation.theme

import androidx.compose.runtime.Composable

@Composable
fun PreviewTheme(
    content: @Composable () -> Unit,
) {
    ShoppingCenterTheme {
        content()
    }
}