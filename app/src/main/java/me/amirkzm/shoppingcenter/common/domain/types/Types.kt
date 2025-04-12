package me.amirkzm.shoppingcenter.common.domain.types

import androidx.compose.runtime.Composable

typealias UnitCallback = ()-> Unit
typealias ComposableUnitCallback = @Composable ()-> Unit
typealias ClickableComposableUnitCallback = @Composable (onClick: UnitCallback)-> Unit