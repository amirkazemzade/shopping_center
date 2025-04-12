package me.amirkzm.shoppingcenter.common.data.clients

import android.util.Log
import io.ktor.client.HttpClient
import me.amirkzm.shoppingcenter.common.data.clients.configs.defaultConfigs
import me.amirkzm.shoppingcenter.common.data.clients.configs.networkCheckConfig
import me.amirkzm.shoppingcenter.common.domain.client.UrlProvider
import me.amirkzm.shoppingcenter.common.domain.services.ConnectivityObserver
import javax.inject.Inject

class BaseClientImpl @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val urlProvider: UrlProvider,
) : BaseClient {
    override val client: HttpClient
        get() {
            val c =  HttpClient {
                networkCheckConfig(connectivityObserver)
                defaultConfigs(urlProvider)
            }
            Log.d("BASE_CLIENT", c.attributes.allKeys.toString())
            Log.d("BASE_CLIENT", c.requestPipeline.attributes.toString())
            return c
        }
}