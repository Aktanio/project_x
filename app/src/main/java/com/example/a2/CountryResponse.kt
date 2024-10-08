package com.example.a2

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    val name: Name,
    val capital: List<String>?,
    val flags: Flags,
    val subregion: String,
    val currencies: Map<String, Currency>
) {
    @Serializable
    data class Name(
        val common: String
    )
    @Serializable
    data class Flags(
        val png: String,
    )
    @Serializable
    data class Currency(
        val name: String,
    )
}
