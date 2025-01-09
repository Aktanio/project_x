package com.example.a2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.CountryEntity
import com.example.a2.data.NetworkUtils
import com.example.a2.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class CountrySearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>,
    private val connectChecker: NetworkUtils
): ViewModel() {
    private val _countryInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val countryInfoSharedFlow: SharedFlow<String> = _countryInfoSharedFlow

    private val _errorSharedFlow = MutableSharedFlow<String>(replay = 0)
    val errorSharedFlow: SharedFlow<String> = _errorSharedFlow

    fun onSearchButtonClicked(countryName: String) = viewModelScope.launch {
        if (!connectChecker.isInternetAvailable()){
            _errorSharedFlow.emit("Нет подключение к интернету. Проверьте соединение.")
        }

        val country = countryRepository.getCountryByName(countryName)
        val jsonInfoCountry = Json.encodeToString(CountryEntity.serializer(), country)
        _countryInfoSharedFlow.emit(jsonInfoCountry)

    }
}