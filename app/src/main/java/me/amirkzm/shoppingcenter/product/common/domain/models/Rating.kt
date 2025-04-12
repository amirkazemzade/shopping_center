package me.amirkzm.shoppingcenter.product.common.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val count: Int,
    val rate: Double,
)