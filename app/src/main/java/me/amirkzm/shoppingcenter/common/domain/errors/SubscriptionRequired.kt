package me.amirkzm.shoppingcenter.common.domain.errors

import me.amirkzm.shoppingcenter.common.domain.models.ErrorType
import me.amirkzm.shoppingcenter.common.domain.models.RequestError

data class SubscriptionRequired(
    override val message: String = "Subscription required",
) : RequestError {
    override val errorType: ErrorType
        get() = ErrorType.RequestError

    companion object {
        operator fun invoke(message: String?) =
            SubscriptionRequired(message ?: "Subscription required")
    }
}