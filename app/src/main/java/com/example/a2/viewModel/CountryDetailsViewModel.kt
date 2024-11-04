package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2.data.CountryResponse

class CountryDetailsViewModel: ViewModel() {
    private val _countryDetailsLiveData = MutableLiveData<CountryResponse>()
    val countryDetailsLiveData: LiveData<CountryResponse> = _countryDetailsLiveData

    fun infoCountry(country: CountryResponse){
        _countryDetailsLiveData.value = country
    }
}