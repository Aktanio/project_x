package com.example.artworks.domain.entity


data class ArtworkEntity(
    val id: Int,
    val titleArt: String,
    val imageArtId: String?,
    val artistArtDisplay: String?,
    val dateArtDisplay: String?,
    val styleArtTitle: String?
)

