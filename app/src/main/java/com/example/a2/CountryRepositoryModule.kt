package com.example.a2

import dagger.Binds
import dagger.Module

@Module
abstract class CountryRepositoryModule {
    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository
}