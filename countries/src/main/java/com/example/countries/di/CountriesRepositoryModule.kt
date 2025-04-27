package com.example.countries.di

import com.example.countries.data.repository.CountryRepositoryImpl
import com.example.countries.domain.entity.CountryEntity
import com.example.countries.domain.repository.CountryRepository
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