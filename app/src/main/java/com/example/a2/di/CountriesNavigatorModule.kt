package com.example.a2.di

import com.example.countries.presentation.implementation.CountriesNavigatorImpl
import com.example.countries_api.contract.CountriesNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CountriesNavigatorModule {
    @Binds
    abstract fun bindCountiesNavigator(countriesNavigatorImpl: CountriesNavigatorImpl): CountriesNavigator
}