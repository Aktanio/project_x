package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.repository.CountryRepository
import com.example.a2.data.CountryResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {
    private val _countriesListLiveData = MutableLiveData<List<CountryResponse>>()
    val countriesLiveData: LiveData<List<CountryResponse>> = _countriesListLiveData

    fun requestAllCountries(){
        viewModelScope.launch {
            _countriesListLiveData.value = countryRepository.getAllCountries()
        }
    }
}