package com.capstone.artwhale.data.datasource.recent

import com.capstone.artwhale.data.dto.RecentSearchDto
import kotlinx.coroutines.flow.Flow

interface RecentSearchDataSource {

    suspend fun getRecentSearch(): Flow<List<RecentSearchDto>>
    suspend fun insertRecentSearch(keyword: RecentSearchDto)
    suspend fun deleteRecentSearch(keyword: RecentSearchDto)
}