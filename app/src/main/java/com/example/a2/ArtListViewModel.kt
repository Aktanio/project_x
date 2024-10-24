package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworksResponse.Artwork>>()
    val artListLiveData: LiveData<List<ArtworksResponse.Artwork>> = _artListLiveData

    fun getAllArtworks(page: Int = 1){
        viewModelScope.launch {
            _artListLiveData.value = artRepository.getAllArtworks(page)
        }
    }
}