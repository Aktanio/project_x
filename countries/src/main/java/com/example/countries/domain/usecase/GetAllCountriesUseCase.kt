package com.example.countries.domain.usecase

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
) {
    suspend fun invoke(): List<CountryEntity>{
        return countryRepository.getAllCountries()
    }
}