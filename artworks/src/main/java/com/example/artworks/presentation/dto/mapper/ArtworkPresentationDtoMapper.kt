package com.example.artworks.presentation.dto.mapper

import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.presentation.dto.ArtworksPresentationDto

object ArtworkPresentationDtoMapper {
    fun ArtworkEntity.toArtworksPresentationDto() = ArtworksPresentationDto(
        imageArtId = this.imageArtId,
        id = this.id,
        titleArt = this.titleArt,
        artistArtDisplay = this.artistArtDisplay,
        dateArtDisplay = this.dateArtDisplay,
        styleArtTitle = this.styleArtTitle
    )
}