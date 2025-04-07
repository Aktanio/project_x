package com.example.countries.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.AppError
import com.example.countries.domain.usecase.contract.GetCountryByNameUseCase
import com.example.countries.presentation.dto.CountryPresentationDto
import com.example.countries.presentation.dto.mapper.CountryPresentationDtoMapper.toCountryPresentationDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class CountrySearchViewModel @Inject constructor(
    private val getCountryByNameUseCase: GetCountryByNameUseCase
): ViewModel() {
    private val _countryInfoSharedFlow = MutableSharedFlow<String>(replay = 0)
    val countryInfoSharedFlow: SharedFlow<String> = _countryInfoSharedFlow

    private val _errorSharedFlow = MutableSharedFlow<AppError>(replay = 0)
    val errorSharedFlow: SharedFlow<AppError> = _errorSharedFlow

    fun onSearchButtonClicked(countryName: String) = viewModelScope.launch {
        try {
            val country = getCountryByNameUseCase.invoke(countryName)
            val jsonInfoCountry = Json.encodeToString(CountryPresentationDto.serializer(), country.toCountryPresentationDto())
            _countryInfoSharedFlow.emit(jsonInfoCountry)
        }catch (e: Exception){
            _errorSharedFlow.emit(AppError.NoDataError)
        }
    }
}