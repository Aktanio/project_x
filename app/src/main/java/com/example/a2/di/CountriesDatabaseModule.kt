package com.example.a2.di

import android.content.Context
import com.example.a2.data.db.CountriesDao
import com.example.a2.data.db.CountriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CountriesDatabaseModule {

    @Provides
    fun provideCountriesDatabase(@ApplicationContext context: Context): CountriesDatabase{
        return CountriesDatabase.invoke(context)
    }

    @Provides
    fun provideCountriesDao(database: CountriesDatabase): CountriesDao{
        return database.getCountriesDao()
    }
}