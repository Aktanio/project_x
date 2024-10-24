package com.example.a2

 interface ArtRepository {
     suspend fun getAllArtworks(page: Int): List<ArtworksResponse.Artwork>
     suspend fun getArtByName(artName: String): ArtworksResponse.Artwork
}