package com.example.countries.presentation.dto.mapper

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.presentation.dto.CountryPresentationDto

object CountryPresentationDtoMapper {
    fun CountryEntity.toCountryPresentationDto() = CountryPresentationDto(
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
}