package com.example.artworks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artworks.data.ArtworkEntity
import kotlinx.serialization.json.Json

class ArtDetailsViewModel: ViewModel() {
    private val _artDetailsLiveData = MutableLiveData<ArtworkEntity>()
    val artDetailLiveData: LiveData<ArtworkEntity> = _artDetailsLiveData

    fun onArtReceived(artJson: String){
        requestArt(artJson)
    }

    private fun requestArt(art: String){
        val jsonInfoArt = Json.decodeFromString(ArtworkEntity.serializer(), art)
        _artDetailsLiveData.value = jsonInfoArt
    }
}