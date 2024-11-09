package com.example.a2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.repository.ArtRepository
import com.example.a2.data.ArtworksResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworksResponse.Artwork>>()
    val artListLiveData: LiveData<List<ArtworksResponse.Artwork>> = _artListLiveData

    fun requestAllArtworks(page: Int = 1){
        viewModelScope.launch {
            _artListLiveData.value = artRepository.getAllArtworks(page)
        }
        Log.d("listViewModel","viewModel создалась")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("listViewModel","viewmodel стерлась")
    }
}