package me.amirkzm.shoppingcenter.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.amirkzm.shoppingcenter.common.data.clients.BaseClient
import me.amirkzm.shoppingcenter.common.data.clients.BaseClientImpl
import me.amirkzm.shoppingcenter.common.data.clients.providers.UrlProviderImp
import me.amirkzm.shoppingcenter.common.data.services.connectivityobserver.ConnectivityObserverModernImpl
import me.amirkzm.shoppingcenter.common.domain.client.UrlProvider
import me.amirkzm.shoppingcenter.common.domain.services.ConnectivityObserver

@InstallIn(SingletonComponent::class)
@Module
abstract class CommonDIModule {
    @Binds
    abstract fun bindBaseClient(
        baseClientImpl: BaseClientImpl,
    ): BaseClient

    @Binds
    abstract fun bindUrlProvider(
        urlProviderImp: UrlProviderImp,
    ): UrlProvider

    @Binds
    abstract fun bindConnectivityObserver(
        connectivityObserverModernImpl: ConnectivityObserverModernImpl,
    ): ConnectivityObserver
}