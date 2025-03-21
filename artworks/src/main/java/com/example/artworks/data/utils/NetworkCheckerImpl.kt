package com.example.artworks.data.utils

import com.example.artworks.domain.utils.NetworkChecker
import javax.inject.Inject

class NetworkCheckerImpl @Inject constructor(
    private val networksUtils: NetworksUtils
): NetworkChecker {
    override suspend fun isNetworkAvailable(): Boolean {
        return networksUtils.isNetworkAvailable()
    }
}