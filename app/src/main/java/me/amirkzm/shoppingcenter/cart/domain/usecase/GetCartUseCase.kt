package me.amirkzm.shoppingcenter.cart.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.cart.domain.repositories.CartRepository
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(): Flow<RequestState<List<CartItemModel>>> = flow {
        emit(RequestState.Loading)
        cartRepository.getCart().collect { requestState ->
            emit(requestState)
        }
    }
}