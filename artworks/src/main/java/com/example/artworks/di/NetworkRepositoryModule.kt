package com.example.artworks.di

import com.example.artworks.data.utils.NetworkRepositoryImpl
import com.example.artworks.domain.utils.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryModule {
    @Binds
    abstract fun bindNetworkChecker(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}