package me.amirkzm.shoppingcenter.common.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle

@Composable
fun MessageTextWithTrailingLoading(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        modifier = modifier
    ) {
        val infiniteTransition =
            rememberInfiniteTransition(label = "Full Screen Infinite Loading Transition")

        val progress by infiniteTransition.animateValue(
            initialValue = 0,
            targetValue = 4,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000, easing = LinearEasing
                )
            ),
            label = "Three Dots Loading"
        )
        Text(text = message, color = color, style = style)
        TextWithStageVisibility(progress = progress, stage = 1, color = color, style = style)
        TextWithStageVisibility(progress = progress, stage = 2, color = color, style = style)
        TextWithStageVisibility(progress = progress, stage = 3, color = color, style = style)
    }
}

@Composable
private fun TextWithStageVisibility(
    progress: Int,
    stage: Int,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = " .",
        color = color,
        style = style,
        modifier = Modifier.stagedVisibility(progress, stage)
    )
}

@Composable
private fun Modifier.stagedVisibility(currentStage: Int, visibilityStage: Int): Modifier =
    this.graphicsLayer { alpha = conditionalVisibility(currentStage >= visibilityStage) }


private fun conditionalVisibility(visible: Boolean) = if (visible) 1f else 0f