package me.amirkzm.shoppingcenter.cart.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource

interface CartRepository {

    fun getCart(): Flow<RequestResource<List<CartItemModel>>>
    suspend fun addItem(cartItemModel: CartItemModel): RequestResource<Unit>
    suspend fun decreaseItemQuantity(id: Int): RequestResource<Unit>
    suspend fun increaseItemQuantity(id: Int): RequestResource<Unit>
    suspend fun removeItem(id: Int): RequestResource<Unit>

    suspend fun clearCart(): RequestResource<Unit>
}