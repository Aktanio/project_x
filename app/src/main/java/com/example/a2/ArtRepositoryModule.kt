package com.example.a2

import dagger.Binds
import dagger.Module

@Module
 abstract class ArtRepositoryModule {
     @Binds
     abstract fun bindArtRepository(
         artRepositoryImpl: ArtRepositoryImpl
     ): ArtRepository
}