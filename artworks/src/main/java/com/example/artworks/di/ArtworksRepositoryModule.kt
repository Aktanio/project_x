package com.example.artworks.di

import com.example.artworks.data.ArtRepository
import com.example.artworks.data.ArtRepositoryImpl
import com.example.artworks.data.ArtworkEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtworksRepositoryModule {
    @Binds
    abstract fun bindRepository(
        artRepositoryImpl: ArtRepositoryImpl
    ):ArtRepository<ArtworkEntity>
}