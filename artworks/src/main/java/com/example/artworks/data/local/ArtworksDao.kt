package com.example.artworks.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artworks.data.local.model.ArtworkDBEntity


@Dao
interface ArtworksDao {
    @Query("SELECT * FROM artworks")
    suspend fun getAllCachedArtworks(): List<ArtworkDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artworks: List<ArtworkDBEntity>)
}