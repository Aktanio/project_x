package com.example.a2.repository

import com.example.a2.data.ArtworkEntity
import com.example.a2.data.ArtworksAPI
import com.example.a2.data.ArtworksResponse
import com.example.a2.data.db.ArtworksDao
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artworksAPI: ArtworksAPI,
    private val artworksDao: ArtworksDao
): ArtRepository {
    override suspend fun getAllArtworks(page: Int): List<ArtworksResponse.Artwork> {
        return artworksAPI.getAllArtworks(page).data
    }

    override suspend fun getArtByName(artName: String): ArtworksResponse.Artwork {
        return artworksAPI.getArtByName(artName).data[0]
    }

    override suspend fun getCachedArtworks(): List<ArtworkEntity> {
        return artworksDao.getAllCachedArtworks()
    }

    override suspend fun insertAllArtworks(artworks: List<ArtworkEntity>) {
        artworksDao.insertAll(artworks)
    }
}