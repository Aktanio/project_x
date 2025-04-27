package com.example.countries.domain.usecase.implementation

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import com.example.countries.domain.usecase.contract.GetCachedCountriesUseCase
import javax.inject.Inject

class GetCachedCountriesUseCaseImpl @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): GetCachedCountriesUseCase {

    override suspend fun invoke(): List<CountryEntity> {
        return countryRepository.getCachedCountries()
    }
}