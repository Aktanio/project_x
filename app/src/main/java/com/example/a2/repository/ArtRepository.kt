package com.example.a2.repository

import com.example.a2.data.ArtworkEntity
import com.example.a2.data.ArtworksResponse

interface ArtRepository {
     suspend fun getAllArtworks(page: Int): List<ArtworksResponse.Artwork>
     suspend fun getArtByName(artName: String): ArtworksResponse.Artwork
     suspend fun getCachedArtworks(): List<ArtworkEntity>
     suspend fun insertAllArtworks(artworks: List<ArtworkEntity>)
}