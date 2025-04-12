package me.amirkzm.shoppingcenter.product.common.domain.repositories

import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel

interface ProductRepository {
    suspend fun getProducts(): RequestState<ProductsListModel>

    suspend fun getProduct(id: Int): RequestState<ProductItemModel>
}