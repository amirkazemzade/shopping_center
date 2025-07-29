package me.amirkzm.shoppingcenter.cart.domain.errors

import me.amirkzm.shoppingcenter.common.domain.models.ErrorType
import me.amirkzm.shoppingcenter.common.domain.models.RequestError

data class DatabaseError(
    val databaseMessage: String? = null,
) : RequestError {
    override val message: String = databaseMessage ?: "Something Went Wrong In Database Transaction"
    override val errorType: ErrorType = ErrorType.DatabaseError

    constructor(throwable: Throwable) : this(throwable.message)
}
