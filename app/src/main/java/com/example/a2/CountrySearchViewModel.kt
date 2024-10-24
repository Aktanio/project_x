package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountrySearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {
    private val _countrySearchLiveData = MutableLiveData<List<CountryResponse>>()
    val countrySearchLiveData: LiveData<List<CountryResponse>> = _countrySearchLiveData

    fun getCountryByName(countryName: String){
        viewModelScope.launch {
            _countrySearchLiveData.value = countryRepository.getCountryByName(countryName)
        }
    }
}