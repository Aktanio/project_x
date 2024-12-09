package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun loadArtworks(page: Int = 1) = viewModelScope.launch {

        if(isLoading) {
            return@launch
        }
        isLoading = true

        val newArtworks = withContext(Dispatchers.IO){
            artRepository.getAllArtworks(page)
        }
        val currentList = _artListLiveData.value.orEmpty()
        _artListLiveData.value = currentList + newArtworks
        isLoading = false
        }

    fun onPageFinished(){
        loadArtworks(++currentPage)
    }
}