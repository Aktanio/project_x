package com.example.a2

data class CountryResponse(
    val name: Name,
    val capital: List<String>?
) {
    data class Name(
        val common: String
    )
}
