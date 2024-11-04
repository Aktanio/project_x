package com.example.a2.repository

import com.example.a2.data.CountryResponse

interface CountryRepository {
    suspend fun getAllCountries(): List<CountryResponse>
    suspend fun getCountryByName(countryName: String): CountryResponse
}