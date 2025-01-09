package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworkEntity
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworkEntity>>()
    val artListLiveData: LiveData<List<ArtworkEntity>> = _artListLiveData

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

        val newArtworks = artRepository.getAllArtworks(page)


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
        val cachedArtworks = artRepository.getCachedArtworks()
        if (cachedArtworks.isNotEmpty()){
            _artListLiveData.value = cachedArtworks
        }
    }

    private suspend fun saveArtworksToDatabase(artworks: List<ArtworkEntity>){
        artRepository.insertAllArtworks(artworks)
    }
}