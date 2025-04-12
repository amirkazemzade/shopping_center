package me.amirkzm.shoppingcenter.common.domain.models

sealed interface RequestSimpleResource {
    data object Success : RequestSimpleResource
    data object Error : RequestSimpleResource
}
