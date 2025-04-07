package com.example.artworks.domain.utils

interface NetworkRepository {
    suspend fun isNetworkAvailable(): Boolean
}