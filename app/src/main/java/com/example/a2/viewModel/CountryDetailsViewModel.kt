package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2.data.CountryResponse
import kotlinx.serialization.json.Json

class CountryDetailsViewModel: ViewModel() {
    private val _countryDetailsLiveData = MutableLiveData<CountryResponse>()
    val countryDetailsLiveData: LiveData<CountryResponse> = _countryDetailsLiveData

    fun onCountryReceived(countryJson: String){
        requestCountry(countryJson)
    }

    private fun requestCountry(country: String){
        val jsonInfoCountry = Json.decodeFromString(CountryResponse.serializer(), country)
        _countryDetailsLiveData.value = jsonInfoCountry
    }
}