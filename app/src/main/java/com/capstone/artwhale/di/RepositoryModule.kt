package com.capstone.artwhale.di

import com.capstone.artwhale.data.repository.*
import com.capstone.artwhale.domain.repository.*
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

    @Singleton
    @Binds
    abstract fun provideAlbumRepository(
        albumRepository: AlbumRepositoryImpl
    ): AlbumRepository

    @Singleton
    @Binds
    abstract fun provideMoodRepository(
        moodRepository: MoodRepositoryImpl
    ): MoodRepository
}