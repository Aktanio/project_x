package com.example.a2.di

import com.example.a2.repository.CountryRepository
import com.example.a2.repository.CountryRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CountryRepositoryModule {
    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository
}