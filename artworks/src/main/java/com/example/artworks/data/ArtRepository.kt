package com.example.artworks.data

interface ArtRepository<T> {
     suspend fun getAllArtworks(page: Int): List<T>
     suspend fun getArtByName(artName: String): T
     suspend fun getCachedArtworks(): List<T>
     suspend fun insertAllArtworks(artworks: List<T>)
}