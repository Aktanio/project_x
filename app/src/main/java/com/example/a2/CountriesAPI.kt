package com.example.a2

import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {
    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name")countryName: String): List<CountryResponce>
}