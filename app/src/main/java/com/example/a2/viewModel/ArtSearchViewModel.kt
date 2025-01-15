package com.example.a2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworkEntity
import com.example.a2.data.db.AppError
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ArtSearchViewModel @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): ViewModel() {
    private val _artInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val artInfoSharedFlow: SharedFlow<String> = _artInfoSharedFlow

    private val _errorSharedFlow = MutableSharedFlow<AppError>(replay = 0)
    val errorSharedFlow: SharedFlow<AppError> = _errorSharedFlow

    fun onSearchButtonClicked(artName:String) = viewModelScope.launch {
        try {
            val art = artRepository.getArtByName(artName)
            val jsonInfoArt = Json.encodeToString(ArtworkEntity.serializer(), art)
            _artInfoSharedFlow.emit(jsonInfoArt)
        }catch (e: Exception){
            _errorSharedFlow.emit(AppError.NoInternetError)
        }
    }
}