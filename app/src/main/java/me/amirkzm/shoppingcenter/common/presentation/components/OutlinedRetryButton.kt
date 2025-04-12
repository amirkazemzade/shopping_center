package me.amirkzm.shoppingcenter.common.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import me.amirkzm.shoppingcenter.common.domain.types.UnitCallback

@Composable
fun OutlinedRetryButton(
    onRetry: UnitCallback,
) {
    OutlinedButtonWithIcon(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "Retry"
            )
        },
        onClick = onRetry,
    ) {
        Text(text = "Retry")
    }
}
