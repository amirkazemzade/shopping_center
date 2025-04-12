package me.amirkzm.shoppingcenter.product.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.amirkzm.shoppingcenter.product.common.data.repositories.ProductRepositoryImp
import me.amirkzm.shoppingcenter.product.common.domain.repositories.ProductRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class ProductModule {

    @Binds
    abstract fun bindProductRepository(
        productRepositoryImp: ProductRepositoryImp,
    ): ProductRepository
}