package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworkEntity
import com.example.a2.data.db.AppError
import com.example.a2.data.db.NetworkUtils
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>,
    private val networkUtils: NetworkUtils
) : ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworkEntity>>()
    val artListLiveData: LiveData<List<ArtworkEntity>> = _artListLiveData

    private val _errorLiveData = MutableLiveData<AppError>()
    val errorLiveData: LiveData<AppError> = _errorLiveData

    companion object {
        const val PAGE_SIZE = 12
    }

    private var currentPage = 1
    private var isLoading = false
    private var isFirstLaunch = true

    init {
        viewModelScope.launch {
            loadCachedArtworks()
            if (_artListLiveData.value.isNullOrEmpty()){
                loadArtworks()
            }
        }
    }

    private suspend fun loadArtworks(page: Int = currentPage) {

        if (isLoading) {
            return
        }
        isLoading = true

        try {

            val newArtworks = artRepository.getAllArtworks(page)
            artRepository.insertAllArtworks(newArtworks)

            val currentList = _artListLiveData.value.orEmpty()
            _artListLiveData.value = currentList + newArtworks

            isFirstLaunch = false

        } catch (e: Exception) {
            if (_artListLiveData.value.orEmpty().isEmpty()) {
                _errorLiveData.value = AppError.NoDataError
            }
        } finally {
            isLoading = false
        }
    }

    fun onPageFinished() {
        if (!isFirstLaunch) {
            viewModelScope.launch {
                loadArtworks(++currentPage)
            }
        }
    }

    private suspend fun loadCachedArtworks() {
        val cachedArtworks = artRepository.getCachedArtworks()
        if (cachedArtworks.isNotEmpty()) {
            _artListLiveData.value = cachedArtworks
            currentPage = (cachedArtworks.size / PAGE_SIZE) + 1
            isFirstLaunch = false
        }
        if (!networkUtils.isNetworkAvailable() && cachedArtworks.isNotEmpty()) {
            _errorLiveData.value = AppError.PartialDataError
        }
    }
}