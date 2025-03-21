package com.example.countries.data.repository

import com.example.countries.data.api.CountriesAPI
import com.example.countries.data.local.CountriesDao
import com.example.countries.data.mapper.CountriesMapper.toCountryDBList
import com.example.countries.data.mapper.CountriesMapper.toCountryEntity
import com.example.countries.data.mapper.CountriesMapper.toCountryEntityDatabaseList
import com.example.countries.data.mapper.CountriesMapper.toCountryEntityResponseList
import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesAPI: CountriesAPI,
    private val countriesDao: CountriesDao
): CountryRepository<CountryEntity> {
    override suspend fun getAllCountries(): List<CountryEntity> {
        return withContext(Dispatchers.IO){
            countriesAPI.getAllCountry().toCountryEntityResponseList()
        }
    }

    override suspend fun getCountryByName(countryName: String): CountryEntity {
        return withContext(Dispatchers.IO){
            countriesAPI.getCountryByName(countryName)[0].toCountryEntity()
        }
    }

    override suspend fun getCachedCountries(): List<CountryEntity> {
        return withContext(Dispatchers.IO){
            countriesDao.getAllCachedCountries().toCountryEntityDatabaseList()
        }
    }

    override suspend fun insertAllCountries(countries: List<CountryEntity>) {
        withContext(Dispatchers.IO){
            countriesDao.insertAll(countries.toCountryDBList())
        }
    }
}