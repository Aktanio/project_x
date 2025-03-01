package com.example.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.CountryEntity
import com.example.countries.AppError
import com.example.countries.data.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class CountrySearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): ViewModel() {
    private val _countryInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val countryInfoSharedFlow: SharedFlow<String> = _countryInfoSharedFlow

    private val _errorSharedFlow = MutableSharedFlow<AppError>(replay = 0)
    val errorSharedFlow: SharedFlow<AppError> = _errorSharedFlow

    fun onSearchButtonClicked(countryName: String) = viewModelScope.launch {
        try {
            val country = countryRepository.getCountryByName(countryName)
            val jsonInfoCountry = Json.encodeToString(CountryEntity.serializer(), country)
            _countryInfoSharedFlow.emit(jsonInfoCountry)
        }catch (e: Exception){
            _errorSharedFlow.emit(AppError.NoDataError)
        }
    }
}