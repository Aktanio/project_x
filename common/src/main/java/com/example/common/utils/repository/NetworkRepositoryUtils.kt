package com.example.common.utils.repository

interface NetworkRepositoryUtils {
    suspend fun isNetworkAvailable(): Boolean
}