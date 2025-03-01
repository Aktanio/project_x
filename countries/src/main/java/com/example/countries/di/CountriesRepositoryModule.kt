package com.example.countries.di

import com.example.countries.data.CountryEntity
import com.example.countries.data.CountryRepository
import com.example.countries.data.CountryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CountriesRepositoryModule {
    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository<CountryEntity>
}