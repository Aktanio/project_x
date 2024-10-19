package com.example.a2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCountriesClient {
    private const val MAIN_URL = "https://restcountries.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(MAIN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val countriesAPI = retrofit.create(CountriesAPI::class.java)
}