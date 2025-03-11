package com.example.artworks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.AppError
import com.example.artworks.domain.usecase.GetArtByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ArtSearchViewModel @Inject constructor(
    private val getArtByNameUseCase: GetArtByNameUseCase
): ViewModel() {
    private val _artInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val artInfoSharedFlow: SharedFlow<String> = _artInfoSharedFlow

    private val _errorSharedFlow = MutableSharedFlow<AppError>(replay = 0)
    val errorSharedFlow: SharedFlow<AppError> = _errorSharedFlow

    fun onSearchButtonClicked(artName:String) = viewModelScope.launch {
        try {
            val art = getArtByNameUseCase.invoke(artName)
            val jsonInfoArt = Json.encodeToString(ArtworkEntity.serializer(), art)
            _artInfoSharedFlow.emit(jsonInfoArt)
        }catch (e: Exception){
            _errorSharedFlow.emit(AppError.NoDataError)
        }
    }
}