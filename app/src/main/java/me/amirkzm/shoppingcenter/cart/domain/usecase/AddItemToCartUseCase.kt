package me.amirkzm.shoppingcenter.cart.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.cart.domain.repositories.CartRepository
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {

    operator fun invoke(cartItemModel: CartItemModel): Flow<RequestState<Unit>> = flow {
        emit(RequestState.Loading)
        emit(cartRepository.addItem(cartItemModel = cartItemModel))
    }
}