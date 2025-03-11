package com.example.artworks.domain.usecase

import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.entity.ArtworkEntity
import javax.inject.Inject

class GetAllArtworksUseCase @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
) {
    suspend fun invoke(page: Int): List<ArtworkEntity>{
        return artRepository.getAllArtworks(page)
    }
}