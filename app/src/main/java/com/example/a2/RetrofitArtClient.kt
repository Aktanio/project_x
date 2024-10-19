package com.example.a2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitArtClient {
    private const val BASE_URL = "https://api.artic.edu/"
    const val BASE_URL_FOR_IMAGE = "https://www.artic.edu/iiif/2/"
    const val IMAGE_SIZE = "/full/843,/0/default.jpg"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val artAPI = retrofit.create(ArtworksAPI::class.java)

}