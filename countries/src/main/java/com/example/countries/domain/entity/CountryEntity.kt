package com.example.countries.domain.entity


data class CountryEntity(
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