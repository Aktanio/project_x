package com.example.a2.di

import com.example.artworks.presentation.implementation.ArtworksNavigatorImpl
import com.example.artworks_api.contract.ArtworksNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtworksNavigatorModule {
    @Binds
    abstract fun bindArtworksNavigator(artworksNavigatorImpl: ArtworksNavigatorImpl): ArtworksNavigator
}