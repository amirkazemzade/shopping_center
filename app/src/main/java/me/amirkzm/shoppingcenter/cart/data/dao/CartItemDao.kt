package me.amirkzm.shoppingcenter.cart.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.amirkzm.shoppingcenter.cart.data.entities.CartItemEntity

@Dao
interface CartItemDao {
    @Query("SELECT * FROM cart_item")
    fun getAll(): Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cart_item WHERE id = :id")
    suspend fun get(id: Int): CartItemEntity?

    @Insert
    suspend fun insert(cartItemEntity: CartItemEntity)

    @Update
    suspend fun update(cartItemEntity: CartItemEntity)

    @Query("DELETE FROM cart_item WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM cart_item")
    suspend fun deleteAll()
}
