package com.example.a2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworksResponse
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ArtSearchViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    private val _artInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val artInfoSharedFlow: SharedFlow<String> = _artInfoSharedFlow


    fun onSearchButtonClicked(artName:String) = viewModelScope.launch {
        val art = withContext(Dispatchers.IO){
            artRepository.getArtByName(artName)
        }
        val jsonInfoArt = Json.encodeToString(ArtworksResponse.Artwork.serializer(), art)
        _artInfoSharedFlow.emit(jsonInfoArt)
    }
}