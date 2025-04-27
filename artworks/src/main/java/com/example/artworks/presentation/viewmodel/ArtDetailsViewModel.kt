package com.example.artworks.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artworks.presentation.dto.ArtworksPresentationDto
import kotlinx.serialization.json.Json

class ArtDetailsViewModel: ViewModel() {
    private val _artDetailsLiveData = MutableLiveData<ArtworksPresentationDto>()
    val artDetailLiveData: LiveData<ArtworksPresentationDto> = _artDetailsLiveData

    fun onArtReceived(artJson: String){
        requestArt(artJson)
    }

    private fun requestArt(art: String){
        val jsonInfoArt = Json.decodeFromString(ArtworksPresentationDto.serializer(), art)
        _artDetailsLiveData.value = jsonInfoArt
    }
}