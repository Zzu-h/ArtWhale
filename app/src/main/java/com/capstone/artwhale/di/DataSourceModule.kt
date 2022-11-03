package com.capstone.artwhale.di

import com.capstone.artwhale.data.datasource.auth.AuthDataSource
import com.capstone.artwhale.data.datasource.auth.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideAuthDataSource(
        authDataSource: AuthDataSourceImpl
    ): AuthDataSource
}