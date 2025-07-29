package me.amirkzm.shoppingcenter.cart.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.cart.domain.repositories.CartRepository
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import javax.inject.Inject

class DecreaseCartItemQuantityUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {

    operator fun invoke(id: Int): Flow<RequestState<Unit>> = flow {
        emit(RequestState.Loading)
        emit(cartRepository.decreaseItemQuantity(id))
    }
}