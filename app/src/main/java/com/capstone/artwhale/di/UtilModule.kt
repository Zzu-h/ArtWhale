package com.capstone.artwhale.di

import android.content.Context
import com.capstone.artwhale.util.MusicPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Singleton
    @Provides
    fun provideMusicPlayer(@ApplicationContext context: Context): MusicPlayer {
        return MusicPlayer(context)
    }
}