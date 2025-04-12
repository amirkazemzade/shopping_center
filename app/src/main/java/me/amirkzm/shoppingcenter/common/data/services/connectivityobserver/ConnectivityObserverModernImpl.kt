package me.amirkzm.shoppingcenter.common.data.services.connectivityobserver

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import me.amirkzm.shoppingcenter.common.domain.models.ConnectivityStatus
import me.amirkzm.shoppingcenter.common.domain.services.ConnectivityObserver
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.N)
class ConnectivityObserverModernImpl @Inject constructor(
    @ApplicationContext context: Context,
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val connectivityStatus = MutableStateFlow(ConnectivityStatus.Checking)

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            connectivityStatus.update { ConnectivityStatus.Available }
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            connectivityStatus.update { ConnectivityStatus.Losing }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            connectivityStatus.update { ConnectivityStatus.Lost }
        }

        override fun onUnavailable() {
            super.onUnavailable()
            connectivityStatus.update { ConnectivityStatus.Unavailable }
        }
    }

    init {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun currentStatus(): ConnectivityStatus = connectivityStatus.value
}