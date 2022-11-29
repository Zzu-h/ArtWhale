package com.capstone.artwhale.di

import android.content.Context
import com.capstone.artwhale.data.dao.RecentSearchDao
import com.capstone.artwhale.data.database.ArtWhaleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ArtWhaleDataBase {
        return ArtWhaleDataBase.getInstance(context)
    }

    @Provides
    fun provideCartDao(dataBase: ArtWhaleDataBase): RecentSearchDao {
        return dataBase.recentSearchDao()
    }
}