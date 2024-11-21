package com.example.a2.data

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    val name: Name,
    val capital: List<String>?,
    val flags: Flags,
    val region: String,
    val population: Int,
    val area: Double,
    val continents: List<String>,
    val languages: Map<String, String>?,
    val subregion: String?,
    val currencies: Map<String, Currency>?,
    val car: CarsInfo
) {
    @Serializable
    data class Name(
        val common: String,
        val official: String,
        val nativeName: Map<String, NativeName>?
    )
    @Serializable
    data class NativeName(
        val official: String
    )
    @Serializable
    data class Flags(
        val png: String,
    )
    @Serializable
    data class Currency(
        val name: String,
    )
    @Serializable
    data class CarsInfo(
        val signs: List<String>,
        val side: String
    )
}
