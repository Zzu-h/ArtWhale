package com.capstone.artwhale.data.dao

import androidx.room.*
import com.capstone.artwhale.data.database.ArtWhaleDataBase
import com.capstone.artwhale.data.dto.RecentSearchDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface RecentSearchDao {

    @Insert
    fun insert(vararg recentSearchDto: RecentSearchDto)

    @Transaction
    suspend fun insertRecentSearch(recentSearchDto: RecentSearchDto) {
        val search: List<RecentSearchDto> = getRecentSearch().first()
        val isExist = getRecentSearch(recentSearchDto.keyword) != null

        if (isExist) deleteRecentSearch(recentSearchDto)
        else if (search.size >= 30) deleteRecentSearch(search.first())
        insert(recentSearchDto)
    }

    @Query("SELECT * FROM ${ArtWhaleDataBase.recentSearchTable}")
    fun getRecentSearch(): Flow<List<RecentSearchDto>>

    @Delete
    fun deleteRecentSearch(vararg recentSearchDto: RecentSearchDto)

    @Query("SELECT * FROM ${ArtWhaleDataBase.recentSearchTable} WHERE keyword = :query")
    fun getRecentSearch(vararg query: String): RecentSearchDto?
}