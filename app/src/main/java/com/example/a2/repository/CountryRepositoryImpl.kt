package com.example.a2.repository

import com.example.a2.data.CountriesAPI
import com.example.a2.data.CountryEntity
import com.example.a2.data.CountryResponse
import com.example.a2.data.db.CountriesDao
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesAPI: CountriesAPI,
    private val countriesDao: CountriesDao
): CountryRepository {
    override suspend fun getAllCountries(): List<CountryResponse> {
        return countriesAPI.getAllCountry()
    }

    override suspend fun getCountryByName(countryName: String): CountryResponse {
        return countriesAPI.getCountryByName(countryName)[0]
    }

    override suspend fun getCachedCountries(): List<CountryEntity> {
        return countriesDao.getAllCachedCountries()
    }

    override suspend fun insertAllCountries(countries: List<CountryEntity>) {
        countriesDao.insertAll(countries)
    }
}