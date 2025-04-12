package me.amirkzm.shoppingcenter.common.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import me.amirkzm.shoppingcenter.common.domain.types.ComposableUnitCallback
import me.amirkzm.shoppingcenter.common.domain.types.UnitCallback
import me.amirkzm.shoppingcenter.common.presentation.theme.Heights
import me.amirkzm.shoppingcenter.common.presentation.theme.ShoppingCenterTheme
import me.amirkzm.shoppingcenter.common.presentation.theme.Widths

@Composable
fun FullScreenErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onRetry: UnitCallback? = null,
    secondaryActionButton: ComposableUnitCallback? = null,
) {
    ErrorMessage(
        message = message,
        textAlignment = TextAlign.Center,
        onRetry = onRetry,
        secondaryActionButton = secondaryActionButton,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start,
    onRetry: UnitCallback? = null,
    secondaryActionButton: ComposableUnitCallback? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            style = TextStyle(textAlign = textAlignment)
        )
        ErrorMessageActionButtons(
            onRetry = onRetry,
            secondaryActionButton = secondaryActionButton,
        )
    }
}

@Composable
private fun ErrorMessageActionButtons(
    onRetry: UnitCallback?,
    secondaryActionButton: ComposableUnitCallback?,
) {
    if (onRetry != null || secondaryActionButton != null) {
        Heights.ExtraSmall()

        Row {
            if (secondaryActionButton != null) {
                secondaryActionButton()
            }
            if (onRetry != null && secondaryActionButton != null) {
                Widths.Small()
            }
            if (onRetry != null) {
                OutlinedRetryButton(onRetry)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ErrorMessagePreview() {
    ShoppingCenterTheme {
        Scaffold {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) {
                val context = LocalContext.current

                ErrorMessage(
                    message = "This is a test message!"
                )
                Spacer(modifier = Modifier.height(32.dp))
                ErrorMessage(
                    message = "This is a test message!",
                    onRetry = {
                        Toast.makeText(context, "Retry Clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}