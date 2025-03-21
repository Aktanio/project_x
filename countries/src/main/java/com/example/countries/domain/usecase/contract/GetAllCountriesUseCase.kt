package com.example.countries.domain.usecase.contract

import com.example.countries.domain.entity.CountryEntity

interface GetAllCountriesUseCase {
    suspend fun invoke(): List<CountryEntity>
}