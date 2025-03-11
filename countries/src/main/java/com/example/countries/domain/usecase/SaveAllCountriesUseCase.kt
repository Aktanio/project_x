package com.example.countries.domain.usecase

import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import javax.inject.Inject

class SaveAllCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
){
    suspend fun invoke(countries: List<CountryEntity>){
        countryRepository.insertAllCountries(countries)
    }
}