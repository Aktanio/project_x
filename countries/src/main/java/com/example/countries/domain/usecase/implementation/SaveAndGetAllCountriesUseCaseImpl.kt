package com.example.countries.domain.usecase.implementation

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import com.example.countries.domain.usecase.contract.SaveAndGetAllCountriesUseCase
import javax.inject.Inject

class SaveAndGetAllCountriesUseCaseImpl @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): SaveAndGetAllCountriesUseCase{
    override suspend fun invoke(): List<CountryEntity> {
        val countriesFromNetwork = countryRepository.getAllCountries()
        countryRepository.insertAllCountries(countriesFromNetwork)
        return countriesFromNetwork
    }
}