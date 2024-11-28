package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.a2.repository.ArtRepository
import com.example.a2.data.ArtworksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {
    val pagerArtworks = artRepository.getPagedArtworks().cachedIn(viewModelScope)
}