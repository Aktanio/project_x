package com.example.artworks.domain.usecase.implementation

import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.usecase.contract.GetCachedArtworksUseCase
import javax.inject.Inject

class GetCachedArtworksUseCaseImpl @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>,
): GetCachedArtworksUseCase {

    override suspend fun invoke(): List<ArtworkEntity> {
        return artRepository.getCachedArtworks()
    }
}