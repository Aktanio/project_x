package com.example.countries.di

import com.example.countries.domain.usecase.contract.GetAllCountriesUseCase
import com.example.countries.domain.usecase.contract.GetCachedCountriesUseCase
import com.example.countries.domain.usecase.contract.GetCountryByNameUseCase
import com.example.countries.domain.usecase.contract.SaveAllCountriesUseCase
import com.example.countries.domain.usecase.implementation.GetAllCountriesUseCaseImpl
import com.example.countries.domain.usecase.implementation.GetCachedCountriesUseCaseImpl
import com.example.countries.domain.usecase.implementation.GetCountryByNameUseCaseImpl
import com.example.countries.domain.usecase.implementation.SaveAllCountriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetAllCountriesUseCase(
        getAllCountriesUseCaseImpl: GetAllCountriesUseCaseImpl
    ): GetAllCountriesUseCase

    @Binds
    abstract fun bindGetCachedCountriesUseCase(
        getCachedCountriesUseCaseImpl: GetCachedCountriesUseCaseImpl
    ): GetCachedCountriesUseCase

    @Binds
    abstract fun bindGetCountryByNameUseCase(
        getCountryByNameUseCaseImpl: GetCountryByNameUseCaseImpl
    ): GetCountryByNameUseCase

    @Binds
    abstract fun bindSaveAllCountriesUseCase(
        saveAllCountriesUseCaseImpl: SaveAllCountriesUseCaseImpl
    ): SaveAllCountriesUseCase
}