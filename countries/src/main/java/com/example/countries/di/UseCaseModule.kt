package com.example.countries.di

import com.example.countries.domain.usecase.contract.GetCachedCountriesUseCase
import com.example.countries.domain.usecase.contract.GetCountryByNameUseCase
import com.example.countries.domain.usecase.contract.SaveAndGetAllCountriesUseCase
import com.example.countries.domain.usecase.implementation.GetCachedCountriesUseCaseImpl
import com.example.countries.domain.usecase.implementation.GetCountryByNameUseCaseImpl
import com.example.countries.domain.usecase.implementation.SaveAndGetAllCountriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetCachedCountriesUseCase(
        getCachedCountriesUseCaseImpl: GetCachedCountriesUseCaseImpl
    ): GetCachedCountriesUseCase

    @Binds
    abstract fun bindGetCountryByNameUseCase(
        getCountryByNameUseCaseImpl: GetCountryByNameUseCaseImpl
    ): GetCountryByNameUseCase

    @Binds
    abstract fun bindSaveAndGetAllCountriesUseCase(
        saveAndGetAllCountriesUseCaseImpl: SaveAndGetAllCountriesUseCaseImpl
    ): SaveAndGetAllCountriesUseCase
}