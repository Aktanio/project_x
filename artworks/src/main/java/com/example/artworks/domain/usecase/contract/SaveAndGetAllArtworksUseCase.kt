package com.example.artworks.domain.usecase.contract

import com.example.artworks.domain.entity.ArtworkEntity

interface SaveAndGetAllArtworksUseCase {
    suspend fun invoke(page: Int): List<ArtworkEntity>
}