package com.example.a2.di

import android.content.Context
import com.example.a2.db.AppDatabase
import com.example.artworks.db.ArtworksDao
import com.example.countries.db.CountriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.invoke(context)
    }

    @Provides
    fun provideArtworksDao(database: AppDatabase): ArtworksDao {
        return database.getArtworksDao()
    }

    @Provides
    fun provideCountriesDao(database: AppDatabase): CountriesDao {
        return database.getCountryDao()
    }
}