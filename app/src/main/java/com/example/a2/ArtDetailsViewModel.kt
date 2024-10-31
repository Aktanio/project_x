package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtDetailsViewModel: ViewModel() {
    private val _artDetailsLiveData = MutableLiveData<ArtworksResponse.Artwork>()
    val artDetailLiveData: LiveData<ArtworksResponse.Artwork> = _artDetailsLiveData

    fun infoArt(art: ArtworksResponse.Artwork){
        _artDetailsLiveData.value = art
    }
}