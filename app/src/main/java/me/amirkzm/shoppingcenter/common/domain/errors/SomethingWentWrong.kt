package me.amirkzm.shoppingcenter.common.domain.errors

import me.amirkzm.shoppingcenter.common.domain.models.ErrorType
import me.amirkzm.shoppingcenter.common.domain.models.RequestError

class SomethingWentWrong(
    val originalException: Exception? = null,
    val originalMessage: String? = originalException?.message,
    override val errorType: ErrorType = ErrorType.Other,
) : RequestError {
    override val message: String
        get() = "Something Went Wrong"
}