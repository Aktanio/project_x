package com.example.artworks.domain.usecase.contract

import com.example.artworks.domain.entity.ArtworkEntity

interface GetArtByNameUseCase {
    suspend fun invoke(artName: String): ArtworkEntity
}