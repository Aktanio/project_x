package com.example.artworks.domain.entity

import com.example.artworks.data.api.model.ArtworksResponse
import com.example.artworks.data.local.model.ArtworkDBEntity
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkEntity(
    val id: Int,
    val titleArt: String,
    val imageArt_id: String?,
    val artist_artDisplay: String?,
    val date_artDisplay: String?,
    val style_artTitle: String?
)

fun ArtworksResponse.Artwork.toArtworkEntity() = ArtworkEntity(
        imageArt_id = this.image_id,
        id = this.id,
        titleArt = this.title,
        artist_artDisplay = this.artist_display,
        date_artDisplay = this.date_display,
        style_artTitle = this.style_title
)

fun List<ArtworksResponse.Artwork>.toArtworkEntityResponseList() = this.map {
        it.toArtworkEntity()
}

fun ArtworkEntity.toArtworkDBEntity() = ArtworkDBEntity(
        image_id = this.imageArt_id,
        id = this.id,
        title = this.titleArt,
        date_display = this.date_artDisplay,
        artist_display = this.artist_artDisplay,
        style_title = this.style_artTitle
)
fun List<ArtworkEntity>.toArtworkDBList() = this.map {
    it.toArtworkDBEntity()
}

fun ArtworkDBEntity.toArtworkEntity() = ArtworkEntity(
    id = this.id,
    titleArt = this.title,
    imageArt_id = this.image_id,
    artist_artDisplay = this.artist_display,
    style_artTitle = this.style_title,
    date_artDisplay = this.date_display
)
fun List<ArtworkDBEntity>.toArtworkEntityDatabaseList() = this.map {
    it.toArtworkEntity()
}

