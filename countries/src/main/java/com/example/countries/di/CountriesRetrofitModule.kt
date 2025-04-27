package com.example.countries.di

import com.example.countries.data.api.CountriesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object CountriesRetrofitModule {
    private const val COUNTRY_BASE_URL = "https://restcountries.com/"

    @Provides
    @Named("countryRetrofit")
    fun provideCountriesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COUNTRY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCountriesAPI(@Named("countryRetrofit")retrofit: Retrofit): CountriesAPI {
        return retrofit.create(CountriesAPI::class.java)
    }
}