package me.amirkzm.shoppingcenter.common.domain.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

sealed interface RequestResource<out T> : RequestState<T> {
    data class Success<T>(val data: T) : RequestResource<T>
    data class Error(val error: RequestError) : RequestResource<Nothing>
}

fun <A, B> RequestResource<A>.mapData(mapper: (A) -> B): RequestResource<B> {
    return when (this) {
        is RequestResource.Error -> this
        is RequestResource.Success -> RequestResource.Success(mapper(this.data))
    }
}

fun <A> RequestResource<A>.toStringWithNoSensitiveData(): String {
    return when (this) {
        is RequestResource.Error -> "Error: ${error.message}"
        is RequestResource.Success -> javaClass.simpleName
    }
}

suspend fun <T> toResourceCatching(
    suspendCallback: suspend () -> T,
    catch: (e: Exception) -> RequestError,
): RequestResource<T> =
    try {
        val value = suspendCallback()
        RequestResource.Success(value)
    } catch (e: Exception) {
        RequestResource.Error(catch(e))
    }

fun <T> Flow<T>.toResourceFlowCatching(
    catch: (e: Throwable) -> RequestError,
): Flow<RequestResource<T>> = flow {
    this@toResourceFlowCatching
        .catch { throwable ->
            emit(
                RequestResource.Error(catch(throwable))
            )
        }
        .collect { data -> emit(RequestResource.Success(data)) }
}
