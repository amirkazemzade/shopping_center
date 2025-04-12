package me.amirkzm.shoppingcenter.common.data.clients

import android.util.Log
import me.amirkzm.shoppingcenter.common.data.mappers.toResource
import me.amirkzm.shoppingcenter.common.domain.client.Client
import me.amirkzm.shoppingcenter.common.domain.errors.SomethingWentWrong
import me.amirkzm.shoppingcenter.common.domain.exceptions.AppException
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

interface RequestClient : Client {
    override val client: HttpClient
}

const val CLIENT_TAG = "CLIENT"
const val GET_TAG = "$CLIENT_TAG GET"
const val POST_TAG = "$CLIENT_TAG POST"
const val PATCH_TAG = "$CLIENT_TAG PATCH"
const val DELETE_TAG = "$CLIENT_TAG DELETE"

suspend fun <T> RequestClient.get(
    urlString: String,
    responseMapper: suspend HttpResponse.() -> RequestResource<T>,
    block: HttpRequestBuilder.() -> Unit = {},
): RequestResource<T> {
    return try {
        client
            .get { url(urlString); block() }
            .apply { responseMapper() }
            .responseMapper()
    } catch (e: AppException) {
        RequestResource.Error(e.error)
    } catch (e: Exception) {
        Log.e(GET_TAG, e.message, e)
        RequestResource.Error(SomethingWentWrong(originalException = e))
    }
}

suspend inline fun <reified T> RequestClient.get(
    urlString: String,
    noinline block: HttpRequestBuilder.() -> Unit = {},
): RequestResource<T> {
    return get(
        urlString = urlString,
        block = block,
        responseMapper = { toResource<T>() },
    )
}

suspend inline fun <reified T> RequestClient.post(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {},
): RequestResource<T> {
    return try {
        client.post { url(urlString); block() }.toResource<T>()
    } catch (e: AppException) {
        RequestResource.Error(e.error)
    } catch (e: Exception) {
        Log.e(POST_TAG, e.message, e)
        RequestResource.Error(SomethingWentWrong(originalException = e))
    }
}

suspend inline fun <reified T> RequestClient.patch(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {},
): RequestResource<T> {
    return try {
        client.patch { url(urlString); block() }.toResource<T>()
    } catch (e: AppException) {
        RequestResource.Error(e.error)
    } catch (e: Exception) {
        Log.e(PATCH_TAG, e.message, e)
        RequestResource.Error(SomethingWentWrong(originalException = e))
    }
}

suspend inline fun <reified T> RequestClient.delete(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {},
): RequestResource<T> {
    return try {
        client.delete { url(urlString); block() }.toResource<T>()
    } catch (e: AppException) {
        RequestResource.Error(e.error)
    } catch (e: Exception) {
        Log.e(DELETE_TAG, e.message, e)
        RequestResource.Error(SomethingWentWrong(originalException = e))
    }
}
