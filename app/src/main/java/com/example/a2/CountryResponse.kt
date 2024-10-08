package com.example.a2

data class CountryResponse(
    val name: Name,
    val capital: List<String>?,
    val flags: Flags,
    val subregion: String,
    val currencies: Map<String, Currency>
) {
    data class Name(
        val common: String
    )
    data class Flags(
        val png: String,
    )
    data class Currency(
        val name: String,
    )
}
