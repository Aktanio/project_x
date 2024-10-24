package com.example.a2

import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesAPI: CountriesAPI
): CountryRepository {
    override suspend fun getAllCountries(): List<CountryResponse> {
        return countriesAPI.getAllCountry()
    }

    override suspend fun getCountryByName(countryName: String): List<CountryResponse> {
        return countriesAPI.getCountryByName(countryName)
    }
}