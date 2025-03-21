package com.example.artworks.domain.usecase.contract

import com.example.artworks.domain.entity.ArtworkEntity

interface SaveAllArtUseCase {
    suspend fun invoke(artworks: List<ArtworkEntity>)
}