package com.example.a2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.repository.ArtRepository
import com.example.a2.data.ArtworksResponse
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ArtSearchViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    private val _artInfoLiveData = MutableLiveData<String>()
    val artInfoLiveData: LiveData<String> = _artInfoLiveData


    fun onSearchButtonClicked(artName:String) = viewModelScope.launch {
        val art = artRepository.getArtByName(artName)
        val jsonInfoArt = Json.encodeToString(ArtworksResponse.Artwork.serializer(), art)
        _artInfoLiveData.value = jsonInfoArt
        Log.d("searchViewModel","viewmodel создалась")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("searchViewModel","viewmodel уничтожилась")
    }
}