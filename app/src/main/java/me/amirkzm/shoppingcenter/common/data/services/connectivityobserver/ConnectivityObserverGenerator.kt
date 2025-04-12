package me.amirkzm.shoppingcenter.common.data.services.connectivityobserver

import android.content.Context
import android.os.Build
import me.amirkzm.shoppingcenter.common.domain.services.ConnectivityObserver

object ConnectivityObserverGenerator {
    fun generate(context: Context): ConnectivityObserver {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ConnectivityObserverModernImpl(context)
        } else {
            ConnectivityObserverLegacyImpl(context)
        }
    }
}
