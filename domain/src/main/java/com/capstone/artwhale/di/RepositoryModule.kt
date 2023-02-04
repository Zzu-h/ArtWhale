package com.capstone.artwhale.di

import com.capstone.artwhale.domain.repository.*
import com.capstone.artwhale.domain.repository.impl.*
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

    @Singleton
    @Binds
    abstract fun provideRecentSearchRepository(
        recentSearchRepository: RecentSearchRepositoryImpl
    ): RecentSearchRepository

    @Singleton
    @Binds
    abstract fun provideUserRepository(
        userRepository: UserRepositoryImpl
    ): UserRepository
}