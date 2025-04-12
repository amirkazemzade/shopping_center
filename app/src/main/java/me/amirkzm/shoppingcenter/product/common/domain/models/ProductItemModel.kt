package me.amirkzm.shoppingcenter.product.common.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductItemModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)