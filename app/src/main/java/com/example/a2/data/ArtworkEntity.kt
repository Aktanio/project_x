package com.example.a2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "artworks")
data class ArtworkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val image_id: String?,
    val artist_display: String?,
    val date_display: String?,
    val style_title: String?
)
