package com.example.artworks.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArtworksPresentationDto(
    val id: Int,
    val titleArt: String,
    val imageArtId: String?,
    val artistArtDisplay: String?,
    val dateArtDisplay: String?,
    val styleArtTitle: String?
)