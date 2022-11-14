package com.capstone.artwhale.di

import com.capstone.artwhale.data.repository.AuthRepositoryImpl
import com.capstone.artwhale.data.repository.MusicRepositoryImpl
import com.capstone.artwhale.data.repository.NoticeRepositoryImpl
import com.capstone.artwhale.domain.repository.AuthRepository
import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.repository.NoticeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun provideMusicRepository(
        musicRepository: MusicRepositoryImpl
    ): MusicRepository

    @Singleton
    @Binds
    abstract fun provideNoticeRepository(
        noticeRepository: NoticeRepositoryImpl
    ): NoticeRepository
}