package com.example.a2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.CountryResponse
import com.example.a2.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CountrySearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {
    private val _countryInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val countryInfoSharedFlow: SharedFlow<String> = _countryInfoSharedFlow

    fun onSearchButtonClicked(countryName: String) = viewModelScope.launch {
        val country = withContext(Dispatchers.IO){
            countryRepository.getCountryByName(countryName)
        }
        val jsonInfoCountry = Json.encodeToString(CountryResponse.serializer(), country)
        _countryInfoSharedFlow.emit(jsonInfoCountry)

    }
}