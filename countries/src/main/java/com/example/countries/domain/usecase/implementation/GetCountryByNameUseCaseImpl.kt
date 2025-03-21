package com.example.countries.domain.usecase.implementation

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import com.example.countries.domain.usecase.contract.GetCountryByNameUseCase
import javax.inject.Inject

class GetCountryByNameUseCaseImpl @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): GetCountryByNameUseCase {

    override suspend fun invoke(countryName: String): CountryEntity {
        return countryRepository.getCountryByName(countryName)
    }
}