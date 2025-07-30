package me.amirkzm.shoppingcenter.product.common.data.repositories

import me.amirkzm.shoppingcenter.common.data.clients.BaseClient
import me.amirkzm.shoppingcenter.common.data.clients.get
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val client: BaseClient,
) : ProductRepository {
    override suspend fun getProducts(category: Category?): RequestResource<ProductsListModel> =
        client.get("products${if (!category.isNullOrEmpty()) "/category/$category" else ""}")

    override suspend fun getProduct(id: Int): RequestResource<ProductItemModel> =
        client.get("products/$id")

    override suspend fun getCategories(): RequestResource<List<Category>> =
        client.get("products/categories")
}