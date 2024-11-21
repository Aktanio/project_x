package com.example.a2.repository

import com.example.a2.data.CountriesAPI
import com.example.a2.data.CountryResponse
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesAPI: CountriesAPI
): CountryRepository {
    override suspend fun getAllCountries(): List<CountryResponse> {
        return countriesAPI.getAllCountry()
    }

    override suspend fun getCountryByName(countryName: String): CountryResponse {
        return countriesAPI.getCountryByName(countryName)[0]
    }
}