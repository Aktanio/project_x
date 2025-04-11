package com.example.artworks.domain.usecase.contract

import com.example.common.utils.repository.NetworkRepositoryUtils
import javax.inject.Inject

class CheckNetworkUseCase @Inject constructor(
    private val networkRepositoryUtils: NetworkRepositoryUtils
) {
    suspend fun invoke(): Boolean {
        return networkRepositoryUtils.isNetworkAvailable()
    }
}