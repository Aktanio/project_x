package com.example.a2.repository

import com.example.a2.data.CountriesAPI
import com.example.a2.data.CountryEntity
import com.example.a2.data.db.CountriesDao
import com.example.a2.data.toCountryDBList
import com.example.a2.data.toCountryEntity
import com.example.a2.data.toCountryEntityDatabaseList
import com.example.a2.data.toCountryEntityResponseList
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