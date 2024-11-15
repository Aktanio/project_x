package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2.data.ArtworksResponse
import kotlinx.serialization.json.Json

class ArtDetailsViewModel: ViewModel() {
    private val _artDetailsLiveData = MutableLiveData<ArtworksResponse.Artwork>()
    val artDetailLiveData: LiveData<ArtworksResponse.Artwork> = _artDetailsLiveData

    fun requestArt(art: String){
        val jsonInfoArt = Json.decodeFromString(ArtworksResponse.Artwork.serializer(), art)
        _artDetailsLiveData.value = jsonInfoArt
    }
}