package me.amirkzm.shoppingcenter.product.common.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.ProductItemModel
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductItemUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {

    operator fun invoke(
        id: Int,
    ): Flow<RequestState<ProductItemModel>> = flow {
        emit(RequestState.Loading)
        emit(productRepository.getProduct(id))
    }
}