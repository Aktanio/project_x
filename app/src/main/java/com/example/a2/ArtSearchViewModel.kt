package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtSearchViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    private val _artSearchLiveData = MutableLiveData<ArtworksResponse.Artwork>()
    val artSearchLiveData: LiveData<ArtworksResponse.Artwork> = _artSearchLiveData

    fun getArtByName(artName:String){
        viewModelScope.launch {
            _artSearchLiveData.value = artRepository.getArtByName(artName)
        }
    }
}