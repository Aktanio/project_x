package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.CountryEntity
import com.example.a2.repository.CountryRepository
import com.example.a2.data.CountryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {
    private val _countriesListLiveData = MutableLiveData<List<CountryResponse>>()
    val countriesLiveData: LiveData<List<CountryResponse>> = _countriesListLiveData

    fun requestAllCountries() = viewModelScope.launch{

        val cachedCountry = withContext(Dispatchers.IO){
           loadCachedCountries()
        }

        if (cachedCountry.isNotEmpty()){
            _countriesListLiveData.value = cachedCountry
        } else{
            val apiCountries = withContext(Dispatchers.IO){
                countryRepository.getAllCountries()
            }

            saveCountriesToDatabase(apiCountries)

            _countriesListLiveData.value = loadCachedCountries()
        }
    }
    private suspend fun loadCachedCountries(): List<CountryResponse>{
        return withContext(Dispatchers.IO){
            countryRepository.getCachedCountries().map { entity->
                entity.toCountryResponse()
            }
        }
    }

    private suspend fun saveCountriesToDatabase(countries: List<CountryResponse>){
        withContext(Dispatchers.IO){
            val entities = countries.map { it.toCountryEntity() }
            countryRepository.insertAllCountries(entities)
        }
    }

    private fun CountryEntity.toCountryResponse() = CountryResponse(
        name = CountryResponse.Name(
            common = commonName,
            official = officialName,
            nativeName = nativeName?.let { Gson().fromJson(it, object : TypeToken<Map<String, CountryResponse.NativeName>>() {}.type) }
        ),
        capital = listOfNotNull(capital),
        flags = CountryResponse.Flags(png = flagsPng),
        subregion = subregion,
        currencies = currencies?.let { Gson().fromJson(it, object : TypeToken<Map<String, CountryResponse.Currency>>() {}.type) },
        continents = continents.split(", "),
        car = CountryResponse.CarsInfo(
            signs = carSigns.split(", "),
            side = carSide
        ),
        area = area,
        region = region,
        languages = languages?.let { Gson().fromJson(it, object : TypeToken<Map<String, String>>() {}.type) },
        population = population,
        id = id
    )

    private fun CountryResponse.toCountryEntity() = CountryEntity(
        commonName = name.common,
        officialName = name.official,
        nativeName = name.nativeName?.let { Gson().toJson(it) },
        capital = capital?.first(),
        flagsPng = flags.png,
        subregion = subregion,
        currencies = currencies?.let { Gson().toJson(it) },
        continents = continents.joinToString(", "),
        carSigns = car.signs?.joinToString(", ") ?: "",
        carSide = car.side,
        area = area,
        region = region,
        languages = languages?.let { Gson().toJson(it) },
        population = population,
        id = id
    )
}