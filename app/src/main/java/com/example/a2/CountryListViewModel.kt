package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {
    private val _countriesListLiveData = MutableLiveData<List<CountryResponse>>()
    val countriesLiveData: LiveData<List<CountryResponse>> = _countriesListLiveData

    fun getAllCountries(){
        viewModelScope.launch {
            _countriesListLiveData.value = countryRepository.getAllCountries()
        }
    }
}