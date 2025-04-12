package me.amirkzm.shoppingcenter.common.data.mappers

import me.amirkzm.shoppingcenter.common.domain.errors.SimpleError
import me.amirkzm.shoppingcenter.common.domain.errors.SomethingWentWrong
import me.amirkzm.shoppingcenter.common.domain.errors.SubscriptionRequired
import me.amirkzm.shoppingcenter.common.domain.models.ErrorType
import me.amirkzm.shoppingcenter.common.domain.models.RequestResource
//import me.amirkzm.shoppingcenter.features.auth.domain.errors.Forbidden
//import me.amirkzm.shoppingcenter.features.auth.domain.errors.Unauthorized
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Forbidden
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.isSuccess

suspend inline fun <reified T> HttpResponse.toResource(): RequestResource<T> {
    return when {
        status.isSuccess() -> RequestResource.Success(body<T>())
        else -> handleError()
    }
}

suspend inline fun HttpResponse.handleError(): RequestResource.Error {
    val bodyAsText = bodyAsText().ifBlank { null }
    return when {
//        status == HttpStatusCode.Unauthorized ->
//            RequestResource.Error(Unauthorized(message = bodyAsText))
//
//        status == HttpStatusCode.Forbidden ->
//            RequestResource.Error(Forbidden(message = bodyAsText))

        status == HttpStatusCode.PaymentRequired ->
            RequestResource.Error(SubscriptionRequired(message = bodyAsText))

        bodyAsText != null -> RequestResource.Error(
            SimpleError(
                message = bodyAsText,
                errorType = status.toErrorType()
            )
        )

        else -> RequestResource.Error(SomethingWentWrong(errorType = status.toErrorType()))
    }
}

fun HttpStatusCode.isRequestError(): Boolean = value in (300 until 500)
fun HttpStatusCode.isServerError(): Boolean = value in (500 until 600)

fun HttpStatusCode.toErrorType(): ErrorType {
    return when {
        isRequestError() -> ErrorType.RequestError
        isServerError() -> ErrorType.ServerError
        else -> ErrorType.RequestError
    }
}