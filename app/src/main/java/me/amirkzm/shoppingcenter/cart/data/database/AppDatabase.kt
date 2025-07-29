package me.amirkzm.shoppingcenter.cart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.amirkzm.shoppingcenter.cart.data.dao.CartItemDao
import me.amirkzm.shoppingcenter.cart.data.entities.CartItemEntity

@Database(entities = [CartItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
}