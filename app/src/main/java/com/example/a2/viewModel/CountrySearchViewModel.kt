package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.repository.CountryRepository
import com.example.a2.data.CountryResponse
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CountrySearchViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {
    private val _countryInfoLiveData = MutableLiveData<String>()
    val countryInfoLiveData: LiveData<String> = _countryInfoLiveData

    fun onSearchButtonClicked(countryName: String) = viewModelScope.launch {
        val country = countryRepository.getCountryByName(countryName)
        val jsonInfoCountry = Json.encodeToString(ListSerializer(CountryResponse.serializer()), listOf(country))
        _countryInfoLiveData.value = jsonInfoCountry

    }
}