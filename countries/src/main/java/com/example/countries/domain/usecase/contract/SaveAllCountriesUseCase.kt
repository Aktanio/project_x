package com.example.countries.domain.usecase.contract

import com.example.countries.domain.entity.CountryEntity

interface SaveAllCountriesUseCase {
    suspend fun invoke(countries: List<CountryEntity>)
}