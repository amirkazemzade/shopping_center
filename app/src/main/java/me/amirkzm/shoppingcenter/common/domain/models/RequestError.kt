package me.amirkzm.shoppingcenter.common.domain.models

interface RequestError {
    val message: String
    val errorType: ErrorType
}