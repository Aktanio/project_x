package com.example.countries.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "countries")
data class CountryDBEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val commonName: String,
    val officialName: String,
    val nativeName: String?,
    val capital: String?,
    val flagsPng: String,
    val region: String,
    val population: Int,
    val area: Double,
    val continents: String,
    val languages: String?,
    val subregion: String?,
    val currencies: String?,
    val carSigns: String,
    val carSide: String
)