package com.capstone.artwhale.di

import com.capstone.artwhale.BuildConfig
import com.capstone.artwhale.BuildConfig.BASE_URL
import com.capstone.artwhale.data.service.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): Retrofit {
        val client = OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val contentType = "application/json".toMediaType()


        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    /* Create Service */
    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideMusicService(retrofit: Retrofit): MusicService {
        return retrofit.create(MusicService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeService(retrofit: Retrofit): NoticeService {
        return retrofit.create(NoticeService::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumService(retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoodService(retrofit: Retrofit): MoodService {
        return retrofit.create(MoodService::class.java)
    }
}