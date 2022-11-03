package com.capstone.artwhale.di

import com.capstone.artwhale.domain.usecase.auth.LoginUseCase
import com.capstone.artwhale.domain.usecase.auth.impl.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun provideLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase
}