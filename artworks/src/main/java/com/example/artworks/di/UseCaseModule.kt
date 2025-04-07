package com.example.artworks.di

import com.example.artworks.domain.usecase.contract.GetArtByNameUseCase
import com.example.artworks.domain.usecase.contract.GetCachedArtworksUseCase
import com.example.artworks.domain.usecase.contract.SaveAndGetAllArtworksUseCase
import com.example.artworks.domain.usecase.implementation.GetArtByNameUseCaseImpl
import com.example.artworks.domain.usecase.implementation.GetCachedArtworksUseCaseImpl
import com.example.artworks.domain.usecase.implementation.SaveAndGetAllArtworksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetArtByNameUseCase(
        getArtByNameUseCaseImpl: GetArtByNameUseCaseImpl
    ): GetArtByNameUseCase

    @Binds
    abstract fun bindGetCachedArtworksUseCase(
        getCachedArtworksUseCaseImpl: GetCachedArtworksUseCaseImpl
    ): GetCachedArtworksUseCase

    @Binds
    abstract fun bindSaveAndGetAllArtworksUseCase(
        saveAndGetAllArtworksUseCaseImpl: SaveAndGetAllArtworksUseCaseImpl
    ): SaveAndGetAllArtworksUseCase
}