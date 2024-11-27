package com.example.a2.di

import com.example.a2.data.ArtworksAPI
import com.example.a2.data.CountriesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val COUNTRY_BASE_URL = "https://restcountries.com/"
    private const val ART_BASE_URL = "https://api.artic.edu/"
    const val BASE_URL_FOR_IMAGE = "https://www.artic.edu/iiif/2/"
    const val IMAGE_SIZE = "/full/843,/0/default.jpg"

    @Provides
    @Named("artRetrofit")
    fun provideArtRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ART_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideArtAPI(@Named("artRetrofit")retrofit: Retrofit): ArtworksAPI {
        return retrofit.create(ArtworksAPI::class.java)
    }

    @Provides
    @Named("countryRetrofit")
    fun provideCountriesRetrofit(): Retrofit{
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