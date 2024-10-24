package com.example.a2

interface CountryRepository {
    suspend fun getAllCountries(): List<CountryResponse>
    suspend fun getCountryByName(countryName: String): List<CountryResponse>
}