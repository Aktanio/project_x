package com.example.a2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a2.data.ArtworkEntity


@Dao
interface ArtworksDao {
    @Query("SELECT * FROM artworks")
    suspend fun getAllCachedArtworks(): List<ArtworkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artworks: List<ArtworkEntity>)
}