package com.example.a2.repository

import androidx.paging.PagingData
import com.example.a2.data.ArtworksResponse
import kotlinx.coroutines.flow.Flow

interface ArtRepository {
     suspend fun getAllArtworks(page: Int): List<ArtworksResponse.Artwork>
     suspend fun getArtByName(artName: String): ArtworksResponse.Artwork
     fun getPagedArtworks(): Flow<PagingData<ArtworksResponse.Artwork>>
}