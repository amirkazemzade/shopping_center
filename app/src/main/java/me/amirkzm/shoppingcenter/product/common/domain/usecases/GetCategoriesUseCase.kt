package me.amirkzm.shoppingcenter.product.common.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.product.common.domain.models.Category
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {

    operator fun invoke(): Flow<RequestState<List<Category>>> = flow {
        emit(RequestState.Loading)
        emit(productRepository.getCategories())
    }
}