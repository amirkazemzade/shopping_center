package me.amirkzm.shoppingcenter.common.domain.models

sealed interface RequestResource<out T> : RequestState<T>{
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