package com.example.artworks.di

import com.example.artworks.data.utils.NetworkCheckerImpl
import com.example.artworks.domain.utils.NetworkChecker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworksCheckerModule {
    @Binds
    abstract fun bindNetworkChecker(networkCheckerImpl: NetworkCheckerImpl): NetworkChecker
}