package com.example.a2.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.a2.data.ArtworksAPI
import com.example.a2.data.ArtworksPagingSource
import com.example.a2.data.ArtworksResponse
import kotlinx.coroutines.flow.Flow
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

    override fun getPagedArtworks(): Flow<PagingData<ArtworksResponse.Artwork>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {ArtworksPagingSource(artworksAPI)}
        ).flow
    }
}