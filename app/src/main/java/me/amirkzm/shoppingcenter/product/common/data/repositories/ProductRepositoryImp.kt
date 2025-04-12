package me.amirkzm.shoppingcenter.product.common.data.repositories

import me.amirkzm.shoppingcenter.common.data.clients.BaseClient
import me.amirkzm.shoppingcenter.common.data.clients.get
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val client: BaseClient,
) : ProductRepository {
    override suspend fun getProducts(): RequestState<ProductsListModel> =
        client.get("products")

    override suspend fun getProduct(id: Int): RequestState<ProductItemModel> =
        client.get("products/$id")
}