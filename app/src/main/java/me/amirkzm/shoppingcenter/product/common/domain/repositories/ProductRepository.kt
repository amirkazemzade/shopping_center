package me.amirkzm.shoppingcenter.product.common.domain.repositories

import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel

interface ProductRepository {
    suspend fun getProducts(category: Category?): RequestState<ProductsListModel>

    suspend fun getProduct(id: Int): RequestState<ProductItemModel>

    suspend fun getCategories(): RequestState<List<Category>>
}