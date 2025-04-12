package me.amirkzm.shoppingcenter.product.common.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductsListModel
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {

    operator fun invoke(): Flow<RequestState<ProductsListModel>> = flow {
        emit(RequestState.Loading)
        emit(productRepository.getProducts())
    }
}