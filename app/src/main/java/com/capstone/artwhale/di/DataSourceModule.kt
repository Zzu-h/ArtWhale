package com.capstone.artwhale.di

import com.capstone.artwhale.data.datasource.album.AlbumDataSource
import com.capstone.artwhale.data.datasource.album.AlbumDataSourceImpl
import com.capstone.artwhale.data.datasource.auth.AuthDataSource
import com.capstone.artwhale.data.datasource.auth.AuthDataSourceImpl
import com.capstone.artwhale.data.datasource.mood.MoodDataSource
import com.capstone.artwhale.data.datasource.mood.MoodDataSourceImpl
import com.capstone.artwhale.data.datasource.music.MusicDataSource
import com.capstone.artwhale.data.datasource.music.MusicDataSourceImpl
import com.capstone.artwhale.data.datasource.notice.NoticeDataSource
import com.capstone.artwhale.data.datasource.notice.NoticeDataSourceImpl
import com.capstone.artwhale.data.repository.MoodRepositoryImpl
import com.capstone.artwhale.domain.repository.MoodRepository
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

    @Singleton
    @Binds
    abstract fun provideNoticeDataSource(
        noticeDataSource: NoticeDataSourceImpl
    ): NoticeDataSource

    @Singleton
    @Binds
    abstract fun provideMusicDataSource(
        musicDataSource: MusicDataSourceImpl
    ): MusicDataSource

    @Singleton
    @Binds
    abstract fun provideAlbumDataSource(
        albumDataSource: AlbumDataSourceImpl
    ): AlbumDataSource

    @Singleton
    @Binds
    abstract fun provideMoodDataSource(
        moodDataSource: MoodDataSourceImpl
    ): MoodDataSource
}