package com.example.artworks.domain.usecase.contract

import com.example.artworks.domain.utils.NetworkRepository
import javax.inject.Inject

class CheckNetworkUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend fun invoke(): Boolean {
        return networkRepository.isNetworkAvailable()
    }
}