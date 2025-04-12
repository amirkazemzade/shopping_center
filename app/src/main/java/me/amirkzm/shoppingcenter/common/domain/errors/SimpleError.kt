package me.amirkzm.shoppingcenter.common.domain.errors

import me.amirkzm.shoppingcenter.common.domain.models.ErrorType
import me.amirkzm.shoppingcenter.common.domain.models.RequestError

data class SimpleError(
    override val message: String,
    override val errorType: ErrorType,
) : RequestError