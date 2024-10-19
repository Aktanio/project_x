package com.example.a2

import retrofit2.http.GET
import retrofit2.http.Query

interface ArtworksAPI {
    @GET("api/v1/artworks/search")
    suspend fun getArtByName(@Query("q")nameArt: String,
                             @Query("fields")fields: String =
                                 "title, image_id, artist_display, date_display, style_title"): ArtworksResponse

    @GET("api/v1/artworks")
    suspend fun getAllArtworks(@Query("page") page: Int = 1): ArtworksResponse
}
