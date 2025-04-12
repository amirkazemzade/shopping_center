package me.amirkzm.shoppingcenter.common.data.services.connectivityobserver

import android.content.Context
import android.net.ConnectivityManager
import me.amirkzm.shoppingcenter.common.domain.models.ConnectivityStatus
import me.amirkzm.shoppingcenter.common.domain.services.ConnectivityObserver

@Suppress("DEPRECATION")
class ConnectivityObserverLegacyImpl(
    context: Context,
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun currentStatus(): ConnectivityStatus {
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo?.isConnected == true) {
            ConnectivityStatus.Available
        } else {
            ConnectivityStatus.Unavailable
        }
    }
}
