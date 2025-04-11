package com.example.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.common.utils.repository.NetworkRepositoryUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworksRepositoryUtilsImpl @Inject constructor(
    @ApplicationContext private val context: Context
): NetworkRepositoryUtils {
    override suspend fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}