package com.capstone.artwhale.di

import com.capstone.artwhale.domain.usecase.album.GetAlbumRankingUseCase
import com.capstone.artwhale.domain.usecase.album.GetAllAlbumUseCase
import com.capstone.artwhale.domain.usecase.album.impl.GetAlbumRankingUseCaseImpl
import com.capstone.artwhale.domain.usecase.album.impl.GetAllAlbumUseCaseImpl
import com.capstone.artwhale.domain.usecase.auth.LoginUseCase
import com.capstone.artwhale.domain.usecase.auth.impl.LoginUseCaseImpl
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import com.capstone.artwhale.domain.usecase.music.GetNewMusicUseCase
import com.capstone.artwhale.domain.usecase.music.impl.GetMusicChartUseCaseImpl
import com.capstone.artwhale.domain.usecase.music.impl.GetNewMusicUseCaseImpl
import com.capstone.artwhale.domain.usecase.notice.GetNoticeUseCase
import com.capstone.artwhale.domain.usecase.notice.impl.GetNoticeUseCaseImpl
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

    @Singleton
    @Binds
    abstract fun provideGetMusicChartUseCase(
        getMusicChartUseCase: GetMusicChartUseCaseImpl
    ): GetMusicChartUseCase

    @Singleton
    @Binds
    abstract fun provideGetNewMusicUseCase(
        getNewMusicUseCase: GetNewMusicUseCaseImpl
    ): GetNewMusicUseCase

    @Singleton
    @Binds
    abstract fun provideGetNoticeUseCase(
        getNoticeUseCase: GetNoticeUseCaseImpl
    ): GetNoticeUseCase

    @Singleton
    @Binds
    abstract fun provideGetAlbumRankingUseCase(
        getAlbumRankingUseCase: GetAlbumRankingUseCaseImpl
    ): GetAlbumRankingUseCase

    @Singleton
    @Binds
    abstract fun provideGetAllAlbumUseCase(
        getAllAlbumUseCase: GetAllAlbumUseCaseImpl
    ): GetAllAlbumUseCase
}