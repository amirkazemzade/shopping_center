package me.amirkzm.shoppingcenter.common.domain.errors

import me.amirkzm.shoppingcenter.common.domain.models.ErrorType
import me.amirkzm.shoppingcenter.common.domain.models.RequestError

class NetworkConnectionError : RequestError {
    override val message: String
        get() = "Check your Internet connection!"
    override val errorType: ErrorType
        get() = ErrorType.Other
}
