package com.example.countries.domain.usecase.implementation

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import com.example.countries.domain.usecase.contract.GetAllCountriesUseCase
import javax.inject.Inject

class GetAllCountriesUseCaseImpl @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): GetAllCountriesUseCase {

    override suspend fun invoke(): List<CountryEntity> {
        return countryRepository.getAllCountries()
    }
}