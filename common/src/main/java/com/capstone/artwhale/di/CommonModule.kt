package com.capstone.artwhale.di

import android.content.Context
import com.capstone.artwhale.common.LocalPathUtil
import com.capstone.artwhale.common.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideLocalPathUtil(@ApplicationContext context: Context): LocalPathUtil {
        return LocalPathUtil(context)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }
}