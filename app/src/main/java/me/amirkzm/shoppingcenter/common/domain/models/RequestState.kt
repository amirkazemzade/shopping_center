package me.amirkzm.shoppingcenter.common.domain.models

sealed interface RequestState<out T> {
    data object Idle : RequestState<Nothing>
    data object Loading : RequestState<Nothing>
}

val RequestState<Any?>.isLoading: Boolean
    get() = this is RequestState.Loading

val RequestState<Any?>.isSuccess: Boolean
    get() = this is RequestResource.Success

val RequestState<Any?>.isError: Boolean
    get() = this is RequestResource.Error

val RequestState<Any?>.errorOrNull: RequestError?
    get() {
        if (this !is RequestResource.Error) return null
        return this.error
    }

val RequestState<Any?>.errorMessageOrNull: String?
    get() = this.errorOrNull?.message

/** It returns data if the state is successful; otherwise, it throws an exception **/
val <T> RequestState<T>.successDataOrThrow: T
    get() = (this as RequestResource.Success).data

/** It returns data if the state is successful; otherwise, it returns null **/
val <T> RequestState<T>.successDataOrNull: T?
    get() =
        if (this is RequestResource.Success) this.data
        else null

fun <A, B> RequestState<A>.mapData(mapper: (A) -> B): RequestState<B> {
    return when (this) {
        RequestState.Idle -> RequestState.Idle
        RequestState.Loading -> RequestState.Loading
        is RequestResource -> this.mapData(mapper)
    }
}

fun <A> RequestState<A>.toStringWithNoSensitiveData(): String {
    return when (this) {
        is RequestResource -> this.toStringWithNoSensitiveData()
        else -> javaClass.simpleName
    }
}