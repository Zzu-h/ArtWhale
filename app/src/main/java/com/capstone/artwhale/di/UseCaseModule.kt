package com.capstone.artwhale.di

import com.capstone.artwhale.domain.usecase.album.GetAlbumRankingUseCase
import com.capstone.artwhale.domain.usecase.album.GetAllAlbumUseCase
import com.capstone.artwhale.domain.usecase.album.GetLikeAlbumListUseCase
import com.capstone.artwhale.domain.usecase.album.GetMyAlbumListUseCase
import com.capstone.artwhale.domain.usecase.album.impl.GetAlbumRankingUseCaseImpl
import com.capstone.artwhale.domain.usecase.album.impl.GetAllAlbumUseCaseImpl
import com.capstone.artwhale.domain.usecase.album.impl.GetLikeAlbumListUseCaseImpl
import com.capstone.artwhale.domain.usecase.album.impl.GetMyAlbumListUseCaseImpl
import com.capstone.artwhale.domain.usecase.auth.GetMyInfoUseCase
import com.capstone.artwhale.domain.usecase.auth.LoginUseCase
import com.capstone.artwhale.domain.usecase.auth.impl.GetMyInfoUseCaseImpl
import com.capstone.artwhale.domain.usecase.auth.impl.LoginUseCaseImpl
import com.capstone.artwhale.domain.usecase.mood.GetMoodListUseCase
import com.capstone.artwhale.domain.usecase.mood.impl.GetMoodListUseCaseImpl
import com.capstone.artwhale.domain.usecase.music.GetLikeMusicListUseCase
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import com.capstone.artwhale.domain.usecase.music.GetMyMusicListUseCase
import com.capstone.artwhale.domain.usecase.music.GetNewMusicUseCase
import com.capstone.artwhale.domain.usecase.music.impl.GetLikeMusicListUseCaseImpl
import com.capstone.artwhale.domain.usecase.music.impl.GetMusicChartUseCaseImpl
import com.capstone.artwhale.domain.usecase.music.impl.GetMyMusicListUseCaseImpl
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

    @Singleton
    @Binds
    abstract fun provideGetLikeAlbumListUseCase(
        getLikeAlbumListUseCase: GetLikeAlbumListUseCaseImpl
    ): GetLikeAlbumListUseCase

    @Singleton
    @Binds
    abstract fun provideGetMyAlbumListUseCase(
        getMyAlbumListUseCase: GetMyAlbumListUseCaseImpl
    ): GetMyAlbumListUseCase

    @Singleton
    @Binds
    abstract fun provideGetLikeMusicListUseCase(
        getLikeMusicListUseCase: GetLikeMusicListUseCaseImpl
    ): GetLikeMusicListUseCase

    @Singleton
    @Binds
    abstract fun provideGetMyMusicListUseCase(
        getMyMusicListUseCase: GetMyMusicListUseCaseImpl
    ): GetMyMusicListUseCase

    @Singleton
    @Binds
    abstract fun provideGetMyInfoUseCase(
        getMyInfoUseCase: GetMyInfoUseCaseImpl
    ): GetMyInfoUseCase

    @Singleton
    @Binds
    abstract fun provideGetMoodListUseCase(
        getMoodListUseCase: GetMoodListUseCaseImpl
    ): GetMoodListUseCase
}