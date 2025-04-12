package me.amirkzm.shoppingcenter.common.data.clients.providers

import me.amirkzm.shoppingcenter.common.domain.client.UrlProvider
import javax.inject.Inject

class UrlProviderImp @Inject constructor() : UrlProvider {
    val baseUrlValue = "fakestoreapi.com"
    override fun getBaseUrl(): String = baseUrlValue
}