package com.example.a2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.ArtworkEntity
import com.example.a2.data.NetworkUtils
import com.example.a2.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ArtSearchViewModel @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>,
    private val connectivityChecker: NetworkUtils
): ViewModel() {
    private val _artInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val artInfoSharedFlow: SharedFlow<String> = _artInfoSharedFlow

    private val _errorSharedFlow = MutableSharedFlow<String>(replay = 0)
    val errorSharedFlow: SharedFlow<String> = _errorSharedFlow

    fun onSearchButtonClicked(artName:String) = viewModelScope.launch {
        if (!connectivityChecker.isInternetAvailable()){
            _errorSharedFlow.emit("Нет подключение к интернету. Проверьте соединение.")
            return@launch
        }

        val art = artRepository.getArtByName(artName)
        val jsonInfoArt = Json.encodeToString(ArtworkEntity.serializer(), art)
        _artInfoSharedFlow.emit(jsonInfoArt)

    }
}