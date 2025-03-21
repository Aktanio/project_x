package com.example.countries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.mapper.CountriesMapper.toCountryPresentationDto
import com.example.countries.presentation.error.AppError
import com.example.countries.domain.usecase.contract.GetAllCountriesUseCase
import com.example.countries.domain.usecase.contract.GetCachedCountriesUseCase
import com.example.countries.domain.usecase.contract.SaveAllCountriesUseCase
import com.example.countries.presentation.dto.CountryPresentationDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val saveAllCountriesUseCase: SaveAllCountriesUseCase,
    private val getCachedCountriesUseCase: GetCachedCountriesUseCase
): ViewModel() {
    private val _countriesListLiveData = MutableLiveData<List<CountryPresentationDto>>()
    val countriesLiveData: LiveData<List<CountryPresentationDto>> = _countriesListLiveData

    private val _errorSharedFlow = MutableSharedFlow<AppError>(replay = 0)
    val errorSharedFlow: SharedFlow<AppError> = _errorSharedFlow

    fun requestAllCountries() = viewModelScope.launch{

        try {
            val cachedCountries = loadCachedCountries()
            if (cachedCountries.isNotEmpty()){
                _countriesListLiveData.value = cachedCountries
            }else{
                val apiCountries = getAllCountriesUseCase.invoke()

                saveAllCountriesUseCase.invoke(apiCountries)

                _countriesListLiveData.value = loadCachedCountries()
            }
        }catch (e: Exception){
            if (loadCachedCountries().isEmpty()){
                _errorSharedFlow.emit(AppError.NoDataError)
            }else{
                _errorSharedFlow.emit(AppError.PartialDataError)
            }
        }
    }
    private suspend fun loadCachedCountries() : List<CountryPresentationDto> {
        return getCachedCountriesUseCase.invoke().map { it.toCountryPresentationDto() }
    }
}