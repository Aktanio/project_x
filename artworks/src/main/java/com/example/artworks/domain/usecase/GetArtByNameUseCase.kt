package com.example.artworks.domain.usecase

import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.entity.ArtworkEntity
import javax.inject.Inject

class GetArtByNameUseCase @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
) {
    suspend fun invoke(artName: String): ArtworkEntity {
        return artRepository.getArtByName(artName)
    }
}