package com.example.artworks.domain.usecase.implementation

import com.example.artworks.domain.entity.ArtworkEntity
import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.usecase.contract.GetArtByNameUseCase
import javax.inject.Inject

class GetArtByNameUseCaseImpl @Inject constructor(
    private val artRepository: ArtRepository<ArtworkEntity>
): GetArtByNameUseCase {

    override suspend fun invoke(artName: String): ArtworkEntity {
        return artRepository.getArtByName(artName)
    }
}