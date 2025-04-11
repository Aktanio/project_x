package com.example.common.utils.di

import android.content.Context
import com.example.common.utils.NetworksRepositoryUtilsImpl
import com.example.common.utils.repository.NetworkRepositoryUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkRepositoryModule {
    @Provides
    fun provideNetworkRepository(
        @ApplicationContext context: Context
    ): NetworkRepositoryUtils {
        return NetworksRepositoryUtilsImpl(context)
    }
}