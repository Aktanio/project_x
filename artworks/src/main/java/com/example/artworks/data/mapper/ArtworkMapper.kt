package com.example.artworks.data.mapper

import com.example.artworks.data.api.model.ArtworksResponse
import com.example.artworks.data.local.model.ArtworkDBEntity
import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.presentation.dto.ArtworksPresentationDto

object ArtworkMapper {

    fun ArtworkEntity.toArtworksPresentationDto() = ArtworksPresentationDto(
        imageArtId = this.imageArtId,
        id = this.id,
        titleArt = this.titleArt,
        artistArtDisplay = this.artistArtDisplay,
        dateArtDisplay = this.dateArtDisplay,
        styleArtTitle = this.styleArtTitle
    )

    fun ArtworksPresentationDto.toArtworkEntity() = ArtworkEntity(
        imageArtId = this.imageArtId,
        id = this.id,
        titleArt = this.titleArt,
        artistArtDisplay = this.artistArtDisplay,
        dateArtDisplay = this.dateArtDisplay,
        styleArtTitle = this.styleArtTitle
    )

    fun ArtworksResponse.Artwork.toArtworkEntity() = ArtworkEntity(
        imageArtId = this.image_id,
        id = this.id,
        titleArt = this.title,
        artistArtDisplay = this.artist_display,
        dateArtDisplay = this.date_display,
        styleArtTitle = this.style_title
    )

    fun List<ArtworksResponse.Artwork>.toArtworkEntityResponseList() = this.map {
        it.toArtworkEntity()
    }

    fun ArtworkEntity.toArtworkDBEntity() = ArtworkDBEntity(
        image_id = this.imageArtId,
        id = this.id,
        title = this.titleArt,
        date_display = this.dateArtDisplay,
        artist_display = this.artistArtDisplay,
        style_title = this.styleArtTitle
    )
    fun List<ArtworkEntity>.toArtworkDBList() = this.map {
        it.toArtworkDBEntity()
    }

    fun ArtworkDBEntity.toArtworkEntity() = ArtworkEntity(
        id = this.id,
        titleArt = this.title,
        imageArtId = this.image_id,
        artistArtDisplay = this.artist_display,
        styleArtTitle = this.style_title,
        dateArtDisplay = this.date_display
    )
    fun List<ArtworkDBEntity>.toArtworkEntityDatabaseList() = this.map {
        it.toArtworkEntity()
    }
}