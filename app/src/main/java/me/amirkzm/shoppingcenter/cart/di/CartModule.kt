package me.amirkzm.shoppingcenter.cart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.amirkzm.shoppingcenter.cart.data.repositories.CartRepositoryLocalImpl
import me.amirkzm.shoppingcenter.cart.domain.repositories.CartRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class CartModule {

    @Binds
    abstract fun bindCartRepository(
        cartRepositoryLocalImpl: CartRepositoryLocalImpl,
    ): CartRepository
}