package com.example.a2.di

import com.example.a2.repository.ArtRepository
import com.example.a2.repository.ArtRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
 abstract class ArtRepositoryModule {
     @Binds
     abstract fun bindArtRepository(
         artRepositoryImpl: ArtRepositoryImpl
     ): ArtRepository
}