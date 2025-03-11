package com.example.countries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.domain.entity.CountryEntity
import kotlinx.serialization.json.Json

class CountryDetailsViewModel: ViewModel() {
    private val _countryDetailsLiveData = MutableLiveData<CountryEntity>()
    val countryDetailsLiveData: LiveData<CountryEntity> = _countryDetailsLiveData

    fun onCountryReceived(countryJson: String){
        requestCountry(countryJson)
    }

    private fun requestCountry(country: String){
        val jsonInfoCountry = Json.decodeFromString(CountryEntity.serializer(), country)
        _countryDetailsLiveData.value = jsonInfoCountry
    }
}