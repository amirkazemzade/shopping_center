package me.amirkzm.shoppingcenter.product.common.domain.repositories

import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel

interface ProductRepository {
    suspend fun getProducts(category: Category?): RequestResource<ProductsListModel>

    suspend fun getProduct(id: Int): RequestResource<ProductItemModel>

    suspend fun getCategories(): RequestResource<List<Category>>
}