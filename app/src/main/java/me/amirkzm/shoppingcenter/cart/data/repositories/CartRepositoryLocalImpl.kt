package me.amirkzm.shoppingcenter.cart.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.amirkzm.shoppingcenter.cart.data.dao.CartItemDao
import me.amirkzm.shoppingcenter.cart.data.entities.CartItemEntity
import me.amirkzm.shoppingcenter.cart.domain.errors.DatabaseError
import me.amirkzm.shoppingcenter.cart.domain.models.CartItemModel
import me.amirkzm.shoppingcenter.cart.domain.repositories.CartRepository
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import me.amirkzm.shoppingcenter.common.domain.models.toResourceCatching
import me.amirkzm.shoppingcenter.common.domain.models.toResourceFlowCatching
import javax.inject.Inject

class CartRepositoryLocalImpl @Inject constructor(
    private val cartItemDao: CartItemDao,
) : CartRepository {

    // --- Mappers ---
    private fun CartItemEntity.toDomain(): CartItemModel {
        return CartItemModel(
            id = this.id, quantity = this.quantity
        )
    }

    private fun CartItemModel.toEntity(): CartItemEntity {
        return CartItemEntity(
            id = this.id,
            quantity = this.quantity,
        )
    }

    // --- Repository Implementation ---
    override fun getCart(): Flow<RequestResource<List<CartItemModel>>> =
        getAllCartItems().toResourceFlowCatching {
            DatabaseError(it)
        }

    override suspend fun addItem(cartItemModel: CartItemModel): RequestResource<Unit> =
        toResourceCatching {
            addItemToCart(cartItemModel)
        }

    override suspend fun decreaseItemQuantity(id: Int): RequestResource<Unit> = toResourceCatching {
        decrementItemQuantity(id)
    }

    override suspend fun increaseItemQuantity(id: Int): RequestResource<Unit> = toResourceCatching {
        incrementItemQuantity(id)
    }

    override suspend fun removeItem(id: Int): RequestResource<Unit> = toResourceCatching {
        removeItemFromCart(id)
    }

    override suspend fun clearCart() = toResourceCatching {
        cartItemDao.deleteAll()
    }

    private suspend fun <T> toResourceCatching(
        suspendCallback: suspend () -> T,
    ): RequestResource<T> = toResourceCatching(
        suspendCallback = suspendCallback, catch = { e -> DatabaseError(e) })


    // --- Cart Operations ---

    private fun getAllCartItems(): Flow<List<CartItemModel>> =
        cartItemDao.getAll().map { entities -> entities.map { it.toDomain() } }

    private suspend fun addItemToCart(cartItem: CartItemModel) {
        // Check if item already exists to update quantity, or insert new
        val existingEntity = cartItemDao.get(cartItem.id)
        if (existingEntity != null) {
            val updatedEntity =
                existingEntity.copy(quantity = existingEntity.quantity + cartItem.quantity)
            cartItemDao.update(updatedEntity)
        } else {
            cartItemDao.insert(cartItem.toEntity())
        }
    }

    private suspend fun removeItemFromCart(itemId: Int) {
        cartItemDao.delete(itemId)
    }

    private suspend fun incrementItemQuantity(itemId: Int) {
        val existingItem = cartItemDao.get(itemId)
        val updatedQuantity = (existingItem?.quantity ?: 0) + 1
        val updatedEntity =
            existingItem?.copy(quantity = updatedQuantity) ?: CartItemEntity(itemId, 1)
        cartItemDao.update(updatedEntity)
    }

    private suspend fun decrementItemQuantity(itemId: Int) {
        val item = cartItemDao.get(itemId)
        if (item != null && item.quantity <= 1) {
            cartItemDao.delete(itemId) // Remove if quantity becomes 0 or less
        } else if (item != null) {
            val updatedQuantity = item.quantity - 1
            val updatedEntity = item.copy(quantity = updatedQuantity)
            cartItemDao.update(updatedEntity)
        }
    }
}