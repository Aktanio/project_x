package com.example.artworks.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artworks.domain.usecase.contract.GetCachedArtworksUseCase
import com.example.artworks.domain.usecase.contract.SaveAndGetAllArtworksUseCase
import com.example.artworks.domain.usecase.contract.CheckNetworkUseCase
import com.example.artworks.presentation.dto.ArtworksPresentationDto
import com.example.artworks.presentation.dto.mapper.ArtworkPresentationDtoMapper.toArtworksPresentationDto
import com.example.common.utils.AppError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val saveAndGetAllArtworksUseCase: SaveAndGetAllArtworksUseCase,
    private val getCachedArtworksUseCase: GetCachedArtworksUseCase,
    private val checkNetworkUseCase: CheckNetworkUseCase
) : ViewModel() {
    private val _artListLiveData = MutableLiveData<List<ArtworksPresentationDto>>()
    val artListLiveData: LiveData<List<ArtworksPresentationDto>> = _artListLiveData

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

            val newArtworks = saveAndGetAllArtworksUseCase.invoke(page)

            val currentList = _artListLiveData.value.orEmpty()
            _artListLiveData.value = currentList + newArtworks.map { it.toArtworksPresentationDto() }

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
        val cachedArtworks = getCachedArtworksUseCase.invoke()
        if (cachedArtworks.isNotEmpty()) {
            _artListLiveData.value = cachedArtworks.map { it.toArtworksPresentationDto() }
            currentPage = (cachedArtworks.size / PAGE_SIZE) + 1
            isFirstLaunch = false
        }
        if (cachedArtworks.isNotEmpty() && !checkNetworkUseCase.invoke()) {
            _errorLiveData.value = AppError.PartialDataError
        }
    }
}