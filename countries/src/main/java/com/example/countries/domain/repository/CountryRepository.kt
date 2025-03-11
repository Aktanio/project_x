package com.example.countries.domain.repository

interface CountryRepository<T> {
    suspend fun getAllCountries(): List<T>
    suspend fun getCountryByName(countryName: String): T
    suspend fun getCachedCountries(): List<T>
    suspend fun insertAllCountries(countries: List<T>)
}