package me.amirkzm.shoppingcenter.common.domain.exceptions

import me.amirkzm.shoppingcenter.common.domain.models.RequestError

open class AppException(
    message: String,
    val error: RequestError,
) : Exception(message)