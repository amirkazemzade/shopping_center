package me.amirkzm.shoppingcenter.cart.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemWithProductModel
import me.amirkzm.shoppingcenter.cart.domain.repositories.CartRepository
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.RequestState
import me.amirkzm.shoppingcenter.common.domain.models.successDataOrNull
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository
import javax.inject.Inject

class GetCartWithProductsUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
) {
    operator fun invoke(): Flow<RequestState<List<CartItemWithProductModel>>> = flow {
        emit(RequestState.Loading)
        cartRepository.getCart().collect { requestState ->
            when (requestState) {
                is RequestResource.Error -> emit(requestState)
                is RequestResource.Success<List<CartItemModel>> -> {
                    val cartItemWithProductDeferred =
                        coroutineScope {
                            requestState.data.map { cartItem ->
                                async {
                                    val product =
                                        productRepository.getProduct(cartItem.id).successDataOrNull
                                    CartItemWithProductModel(product, cartItem.quantity)
                                }
                            }
                        }

                    val cartItemWithProducts = cartItemWithProductDeferred.awaitAll()
                    emit(RequestResource.Success(cartItemWithProducts))
                }
            }
        }
    }
}