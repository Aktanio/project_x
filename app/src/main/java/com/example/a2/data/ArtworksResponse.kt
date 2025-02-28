package com.example.a2.data

import kotlinx.serialization.Serializable

@Serializable
data class ArtworksResponse(
    val data: List<Artwork>,
){
    @Serializable
    data class Artwork(
        val id: Int,
        val title: String,
        val image_id: String?,
        val artist_display: String?,
        val date_display: String?,
        val style_title: String?
    )
}
