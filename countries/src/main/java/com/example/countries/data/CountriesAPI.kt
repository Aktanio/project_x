package com.example.countries.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {
    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name")countryName: String): List<CountryResponse>

    @GET("v3.1/all")
    suspend fun getAllCountry(): List<CountryResponse>
}