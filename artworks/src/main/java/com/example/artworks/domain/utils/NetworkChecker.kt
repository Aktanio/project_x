package com.example.artworks.domain.utils

interface NetworkChecker {
    suspend fun isNetworkAvailable(): Boolean
}