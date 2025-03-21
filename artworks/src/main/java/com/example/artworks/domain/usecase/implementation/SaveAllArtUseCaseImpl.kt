package com.example.artworks.domain.usecase.implementation

import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.usecase.contract.SaveAllArtUseCase
import javax.inject.Inject

class SaveAllArtUseCaseImpl @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): SaveAllArtUseCase {

    override suspend fun invoke(artworks: List<ArtworkEntity>) {
        artRepository.insertAllArtworks(artworks)
    }
}