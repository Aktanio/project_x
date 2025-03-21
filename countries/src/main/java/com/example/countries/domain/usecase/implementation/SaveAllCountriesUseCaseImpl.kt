package com.example.countries.domain.usecase.implementation

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import com.example.countries.domain.usecase.contract.SaveAllCountriesUseCase
import javax.inject.Inject

class SaveAllCountriesUseCaseImpl @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): SaveAllCountriesUseCase{

    override suspend fun invoke(countries: List<CountryEntity>) {
        countryRepository.insertAllCountries(countries)
    }
}