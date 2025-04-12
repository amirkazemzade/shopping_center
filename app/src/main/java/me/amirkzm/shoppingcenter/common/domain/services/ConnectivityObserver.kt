package me.amirkzm.shoppingcenter.common.domain.services

import me.amirkzm.shoppingcenter.common.domain.models.ConnectivityStatus

interface ConnectivityObserver {
    fun currentStatus(): ConnectivityStatus
}