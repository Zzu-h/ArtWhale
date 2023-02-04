package com.capstone.artwhale.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.artwhale.data.dao.RecentSearchDao
import com.capstone.artwhale.data.dto.RecentSearchDto

@Database(
    entities = [RecentSearchDto::class],
    version = 1
)
abstract class ArtWhaleDataBase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao

    companion object {
        const val databaseName: String = "ArtWhale-DataBase"
        const val recentSearchTable: String = "recent_search_table"

        fun getInstance(context: Context): ArtWhaleDataBase =
            Room.databaseBuilder(context, ArtWhaleDataBase::class.java, databaseName).build()
    }
}