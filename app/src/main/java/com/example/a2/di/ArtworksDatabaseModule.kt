package com.example.a2.di

import android.content.Context
import com.example.a2.data.db.ArtworksDao
import com.example.a2.data.db.ArtworksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ArtworksDatabaseModule {

    @Provides
    fun provideArtworksDatabase(@ApplicationContext context: Context): ArtworksDatabase{
        return ArtworksDatabase.invoke(context)
    }

    @Provides
    fun provideArtworksDao(database: ArtworksDatabase): ArtworksDao{
        return database.getArtworksDao()
    }
}