package com.example.artworks.data

import com.example.artworks.db.ArtworksDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artworksAPI: ArtworksAPI,
    private val artworksDao: ArtworksDao
): ArtRepository<ArtworkEntity> {
    override suspend fun getAllArtworks(page: Int): List<ArtworkEntity> {
        return withContext(Dispatchers.IO){
            artworksAPI.getAllArtworks(page).data.toArtworkEntityResponseList()
        }
    }

    override suspend fun getArtByName(artName: String): ArtworkEntity {
        return withContext(Dispatchers.IO){
            artworksAPI.getArtByName(artName).data[0].toArtworkEntity()
        }
    }

    override suspend fun getCachedArtworks(): List<ArtworkEntity> {
        return withContext(Dispatchers.IO){
            artworksDao.getAllCachedArtworks().toArtworkEntityDatabaseList()
        }
    }

    override suspend fun insertAllArtworks(artworks: List<ArtworkEntity>) {
        withContext(Dispatchers.IO){
            artworksDao.insertAll(artworks.toArtworkDBList())
        }
    }
}