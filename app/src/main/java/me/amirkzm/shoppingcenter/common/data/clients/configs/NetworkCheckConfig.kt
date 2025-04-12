package me.amirkzm.shoppingcenter.common.data.clients.configs

import me.amirkzm.shoppingcenter.common.domain.exceptions.NetworkConnectionException
import me.amirkzm.shoppingcenter.common.domain.models.ConnectivityStatus
import me.amirkzm.shoppingcenter.common.domain.services.ConnectivityObserver
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest

fun HttpClientConfig<*>.networkCheckConfig(
    connectivityObserver: ConnectivityObserver,
) {
    install(DefaultRequest) {
        if (connectivityObserver.currentStatus() != ConnectivityStatus.Available) {
            throw NetworkConnectionException()
        }
    }
}