package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworkEntity
import com.example.a2.data.ArtworksResponse
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworksResponse.Artwork>>()
    val artListLiveData: LiveData<List<ArtworksResponse.Artwork>> = _artListLiveData

    private var currentPage = 1
    private var isLoading = false

    init {
        loadCachedArtworks()
    }

    fun loadArtworks(page: Int = 1) = viewModelScope.launch {

        if(isLoading) {
            return@launch
        }
        isLoading = true

        val newArtworks = withContext(Dispatchers.IO){
            artRepository.getAllArtworks(page)
        }

        withContext(Dispatchers.IO){
            saveArtworksToDatabase(newArtworks)
        }

        val currentList = _artListLiveData.value.orEmpty()
        _artListLiveData.value = currentList + newArtworks
        isLoading = false
    }

    fun onPageFinished(){
        loadArtworks(++currentPage)
    }

    private fun loadCachedArtworks() = viewModelScope.launch{
        val cachedArtworks = withContext(Dispatchers.IO){
            artRepository.getCachedArtworks().map { entity->
                entity.toArtworksResponse()
            }
        }
        if (cachedArtworks.isNotEmpty()){
            _artListLiveData.value = cachedArtworks
        }
    }

    private suspend fun saveArtworksToDatabase(artworks: List<ArtworksResponse.Artwork>){
        val artworkEntities = artworks.map { artwork->
            artwork.toArtworkEntity()
        }
        withContext(Dispatchers.IO){
            artRepository.insertAllArtworks(artworkEntities)
        }
    }
    private fun ArtworkEntity.toArtworksResponse() = ArtworksResponse.Artwork(
        title = title,
        style_title = style_title,
        date_display = date_display,
        artist_display = artist_display,
        id = id,
        image_id = image_id
    )
    private fun ArtworksResponse.Artwork.toArtworkEntity() = ArtworkEntity(
        title = title,
        style_title = style_title,
        artist_display = artist_display,
        date_display = date_display,
        id = id,
        image_id = image_id
    )
}