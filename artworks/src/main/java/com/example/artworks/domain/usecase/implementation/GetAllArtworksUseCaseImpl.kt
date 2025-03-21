package com.example.artworks.domain.usecase.implementation

import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.usecase.contract.GetAllArtworksUseCase
import javax.inject.Inject

class GetAllArtworksUseCaseImpl @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): GetAllArtworksUseCase {

    override suspend fun invoke(page: Int): List<ArtworkEntity> {
        return artRepository.getAllArtworks(page)
    }
}