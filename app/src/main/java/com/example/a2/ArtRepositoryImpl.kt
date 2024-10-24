package com.example.a2

import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artworksAPI: ArtworksAPI
): ArtRepository {
    override suspend fun getAllArtworks(page: Int): List<ArtworksResponse.Artwork> {
        return artworksAPI.getAllArtworks().data
    }

    override suspend fun getArtByName(artName: String): ArtworksResponse.Artwork {
        return artworksAPI.getArtByName(artName).data[0]
    }
}