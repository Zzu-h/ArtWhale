package com.capstone.artwhale.data.datasource.recent

import com.capstone.artwhale.data.dao.RecentSearchDao
import com.capstone.artwhale.data.dto.RecentSearchDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentSearchDataSourceImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
) : RecentSearchDataSource {

    override suspend fun getRecentSearch(): Flow<List<RecentSearchDto>> =
        recentSearchDao.getRecentSearch()

    override suspend fun insertRecentSearch(keyword: RecentSearchDto) {
        recentSearchDao.insertRecentSearch(keyword)
    }
}