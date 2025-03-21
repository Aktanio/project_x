package com.example.artworks.di

import com.example.artworks.domain.usecase.contract.GetAllArtworksUseCase
import com.example.artworks.domain.usecase.contract.GetArtByNameUseCase
import com.example.artworks.domain.usecase.contract.GetCachedArtworksUseCase
import com.example.artworks.domain.usecase.contract.SaveAllArtUseCase
import com.example.artworks.domain.usecase.implementation.GetAllArtworksUseCaseImpl
import com.example.artworks.domain.usecase.implementation.GetArtByNameUseCaseImpl
import com.example.artworks.domain.usecase.implementation.GetCachedArtworksUseCaseImpl
import com.example.artworks.domain.usecase.implementation.SaveAllArtUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetAllArtworksUseCase(
        getAllArtworksUseCaseImpl: GetAllArtworksUseCaseImpl
    ): GetAllArtworksUseCase

    @Binds
    abstract fun bindGetArtByNameUseCase(
        getArtByNameUseCaseImpl: GetArtByNameUseCaseImpl
    ): GetArtByNameUseCase

    @Binds
    abstract fun bindGetCachedArtworksUseCase(
        getCachedArtworksUseCaseImpl: GetCachedArtworksUseCaseImpl
    ): GetCachedArtworksUseCase

    @Binds
    abstract fun bindSaveAllArtUseCase(
        saveAllArtUseCaseImpl: SaveAllArtUseCaseImpl
    ): SaveAllArtUseCase
}