package com.example.artworks.domain.usecase.contract

import com.example.artworks.domain.entity.ArtworkEntity

interface GetCachedArtworksUseCase {
    suspend fun invoke(): List<ArtworkEntity>
}