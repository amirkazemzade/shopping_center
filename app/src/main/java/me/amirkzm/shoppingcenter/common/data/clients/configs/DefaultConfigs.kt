package me.amirkzm.shoppingcenter.common.data.clients.configs

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.amirkzm.shoppingcenter.common.domain.client.UrlProvider

fun HttpClientConfig<*>.defaultConfigs(urlProvider: UrlProvider) {
    this.install(ContentNegotiation) {
        /*** Default Configuration for Json ***/
        json(
            json = Json {
                encodeDefaults = true
                isLenient = true
                allowSpecialFloatingPointValues = true
                allowStructuredMapKeys = true
                prettyPrint = false
                useArrayPolymorphism = false
                ignoreUnknownKeys = true
            }
        )
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = urlProvider.getBaseUrl()
        }
        headers.append(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}