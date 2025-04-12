package me.amirkzm.shoppingcenter.common.domain.exceptions

import me.amirkzm.shoppingcenter.common.domain.errors.NetworkConnectionError

class NetworkConnectionException : AppException(
    message = "Check your Internet connection!",
    error = NetworkConnectionError(),
)