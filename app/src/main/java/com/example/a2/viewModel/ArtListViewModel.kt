package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworkEntity
import com.example.a2.data.db.AppError
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworkEntity>>()
    val artListLiveData: LiveData<List<ArtworkEntity>> = _artListLiveData

    private val _errorLiveData = MutableLiveData<AppError>()
    val errorLiveData: LiveData<AppError> = _errorLiveData

    private var currentPage = 1
    private var isLoading = false
    private var isUsingCache = true

    init {
        loadCachedArtworks()
    }

    fun loadArtworks(page: Int = 1) = viewModelScope.launch {

        if(isLoading) {
            return@launch
        }
        isLoading = true

        try {
            if (isUsingCache){
                val cachedArtworks = artRepository.getCachedArtworks()
                if (cachedArtworks.isNotEmpty()){
                    _artListLiveData.value = cachedArtworks
                    currentPage = calculateLastPage(cachedArtworks.size)
                    isUsingCache = false
                    isLoading = false
                    return@launch
                }
            }
            val newArtworks = artRepository.getAllArtworks(page)
            saveArtworksToDatabase(newArtworks)

            val currentList = _artListLiveData.value.orEmpty()
            _artListLiveData.value = currentList + newArtworks

        }catch (e: Exception){
            if (artRepository.getCachedArtworks().isEmpty()){
                _errorLiveData.value = AppError.NoInternetError
            }
        } finally {
            isLoading = false
        }
    }

    fun onPageFinished(){
        if (!isUsingCache){
            loadArtworks(++currentPage)
        }
    }

    private fun loadCachedArtworks() = viewModelScope.launch{
        val cachedArtworks = artRepository.getCachedArtworks()
        if (cachedArtworks.isNotEmpty()){
            _artListLiveData.value = cachedArtworks
            currentPage = calculateLastPage(cachedArtworks.size)
        }
    }

    private fun calculateLastPage(itemCount: Int): Int{
        val itemsPage = 12
        return (itemCount / itemsPage) + if (itemCount % itemsPage == 0) 0 else 1
    }

    private suspend fun saveArtworksToDatabase(artworks: List<ArtworkEntity>){
        artRepository.insertAllArtworks(artworks)
    }
}