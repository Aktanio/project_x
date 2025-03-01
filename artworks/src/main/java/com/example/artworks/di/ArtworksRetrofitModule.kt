package com.example.artworks.di

import com.example.artworks.data.ArtworksAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ArtworksRetrofitModule {

    const val BASE_URL_FOR_IMAGE = "https://www.artic.edu/iiif/2/"
    const val IMAGE_SIZE = "/full/843,/0/default.jpg"
    private const val ART_BASE_URL = "https://api.artic.edu/"

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
        return retrofit.create(com.example.artworks.data.ArtworksAPI::class.java)
    }
}