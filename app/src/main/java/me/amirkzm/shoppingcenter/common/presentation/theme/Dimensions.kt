package me.amirkzm.shoppingcenter.common.presentation.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


object Heights {
    @Composable
    fun Smallest(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.height(4.dp))

    @Composable
    fun ExtraSmall(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.height(8.dp))

    @Composable
    fun Small(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.height(16.dp))

    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.height(20.dp))

    @Composable
    fun Large(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.height(24.dp))

    @Composable
    fun ExtraLarge(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.height(32.dp))
}

object Widths {
    @Composable
    fun ExtraSmall(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.width(8.dp))

    @Composable
    fun Small(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.width(16.dp))

    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.width(20.dp))

    @Composable
    fun Large(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.width(24.dp))

    @Composable
    fun ExtraLarge(
        modifier: Modifier = Modifier,
    ) = Spacer(modifier = modifier.width(32.dp))
}