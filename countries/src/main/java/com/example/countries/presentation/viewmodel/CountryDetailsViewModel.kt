package com.example.countries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.presentation.dto.CountryPresentationDto
import kotlinx.serialization.json.Json

class CountryDetailsViewModel: ViewModel() {
    private val _countryDetailsLiveData = MutableLiveData<CountryPresentationDto>()
    val countryDetailsLiveData: LiveData<CountryPresentationDto> = _countryDetailsLiveData

    fun onCountryReceived(countryJson: String){
        requestCountry(countryJson)
    }

    private fun requestCountry(country: String){
        val jsonInfoCountry = Json.decodeFromString(CountryPresentationDto.serializer(), country)
        _countryDetailsLiveData.value = jsonInfoCountry
    }
}