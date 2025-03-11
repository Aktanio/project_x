package com.example.countries.domain.entity

import com.example.countries.data.api.model.CountryResponse
import com.example.countries.data.local.model.CountryDBEntity
import kotlinx.serialization.Serializable

@Serializable
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

fun CountryResponse.toCountryEntity() = CountryEntity(
    id = this.id,
    commonName = this.name.common,
    officialName = this.name.official,
    nativeName = this.name.nativeName?.values?.joinToString(", "){it.official},
    capital = this.capital?.joinToString(", "),
    flagsPng = this.flags.png,
    region = this.region,
    population = this.population,
    area = this.area,
    continents = this.continents.joinToString(" "),
    languages = this.languages?.values?.joinToString(", "),
    subregion = this.subregion,
    currencies = this.currencies?.values?.joinToString(", "){it.name},
    carSigns = this.car.signs?.joinToString(" ").toString(),
    carSide = this.car.side
)
fun List<CountryResponse>.toCountryEntityResponseList() = this.map {
    it.toCountryEntity()
}

fun CountryEntity.toCountryDBEntity() = CountryDBEntity(
    id = this.id,
    commonName = this.commonName,
    officialName = this.officialName,
    nativeName = this.nativeName,
    capital = this.capital,
    flagsPng = this.flagsPng,
    region = this.region,
    population = this.population,
    area = this.area,
    continents = this.continents,
    languages = this.languages,
    subregion = this.subregion,
    currencies = this.currencies,
    carSigns = this.carSigns,
    carSide = this.carSide
)
fun List<CountryEntity>.toCountryDBList() = this.map {
    it.toCountryDBEntity()
}

fun CountryDBEntity.toCountryEntity() = CountryEntity(
    id = this.id,
    commonName = this.commonName,
    officialName = this.officialName,
    nativeName = this.nativeName,
    capital = this.capital,
    flagsPng = this.flagsPng,
    region = this.region,
    population = this.population,
    area = this.area,
    continents = this.continents,
    languages = this.languages,
    subregion = this.subregion,
    currencies = this.currencies,
    carSigns = this.carSigns,
    carSide = this.carSide
)
fun List<CountryDBEntity>.toCountryEntityDatabaseList() = this.map {
    it.toCountryEntity()
}