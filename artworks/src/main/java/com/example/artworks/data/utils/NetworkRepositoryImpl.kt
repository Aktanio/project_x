package com.example.artworks.data.utils

import com.example.artworks.domain.utils.NetworkRepository
import com.example.common.utils.NetworksUtils
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networksUtils: NetworksUtils
): NetworkRepository {
    override suspend fun isNetworkAvailable(): Boolean {
        return networksUtils.isNetworkAvailable()
    }
}