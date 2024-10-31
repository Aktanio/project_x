package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountryDetailsViewModel: ViewModel() {
    private val _countryDetailsLiveData = MutableLiveData<CountryResponse>()
    val countryDetailsLiveData: LiveData<CountryResponse> = _countryDetailsLiveData

    fun infoCountry(country: List<CountryResponse>){
        _countryDetailsLiveData.value = country[0]
    }
}