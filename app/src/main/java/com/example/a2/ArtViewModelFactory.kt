package com.example.a2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ArtViewModelFactory @Inject constructor(
    private val artRepository: ArtRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ArtRepository::class.java).newInstance(artRepository)
    }
}