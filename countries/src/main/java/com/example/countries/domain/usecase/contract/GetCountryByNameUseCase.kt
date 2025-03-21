package com.example.countries.domain.usecase.contract

import com.example.countries.domain.entity.CountryEntity

interface GetCountryByNameUseCase {
    suspend fun invoke(countryName: String): CountryEntity
}