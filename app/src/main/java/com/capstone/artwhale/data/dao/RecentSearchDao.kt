package com.capstone.artwhale.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.capstone.artwhale.data.database.ArtWhaleDataBase
import com.capstone.artwhale.data.dto.RecentSearchDto
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM ${ArtWhaleDataBase.recentSearchTable}")
    fun getRecentSearch(): Flow<List<RecentSearchDto>>
}