package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.CountryEntity
import com.example.a2.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): ViewModel() {
    private val _countriesListLiveData = MutableLiveData<List<CountryEntity>>()
    val countriesLiveData: LiveData<List<CountryEntity>> = _countriesListLiveData

    fun requestAllCountries() = viewModelScope.launch{

        val cachedCountry = countryRepository.getCachedCountries()

        if (cachedCountry.isNotEmpty()){
            _countriesListLiveData.value = countryRepository.getCachedCountries()
        } else{
            val apiCountries = countryRepository.getAllCountries()

            countryRepository.insertAllCountries(apiCountries)

            _countriesListLiveData.value = countryRepository.getCachedCountries()
        }
    }
}