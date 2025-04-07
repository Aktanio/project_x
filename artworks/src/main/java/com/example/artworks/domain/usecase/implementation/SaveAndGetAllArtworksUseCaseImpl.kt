package com.example.artworks.domain.usecase.implementation

import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.usecase.contract.SaveAndGetAllArtworksUseCase
import javax.inject.Inject

class SaveAndGetAllArtworksUseCaseImpl @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): SaveAndGetAllArtworksUseCase {
    override suspend fun invoke(page: Int): List<ArtworkEntity> {
        val artworksFromNetwork = artRepository.getAllArtworks(page)
        artRepository.insertAllArtworks(artworksFromNetwork)
        return artworksFromNetwork
    }
}