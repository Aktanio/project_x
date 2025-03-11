package com.example.countries.domain.usecase

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
) {
    suspend fun invoke(countryName: String): CountryEntity {
        return countryRepository.getCountryByName(countryName)
    }
}