package com.example.artworks.data.repository

import com.example.artworks.domain.repository.ArtRepository
import com.example.artworks.domain.entity.ArtworkEntity
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
    ): ArtRepository<ArtworkEntity>
}