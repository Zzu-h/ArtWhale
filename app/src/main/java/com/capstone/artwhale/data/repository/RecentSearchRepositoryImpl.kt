package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.recent.RecentSearchDataSource
import com.capstone.artwhale.data.dto.toRecentSearchDto
import com.capstone.artwhale.domain.model.RecentSearch
import com.capstone.artwhale.domain.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchRepositoryImpl @Inject constructor(
    private val recentSearchDataSource: RecentSearchDataSource
) : RecentSearchRepository {

    override suspend fun getRecentSearch(): Flow<List<RecentSearch>> =
        recentSearchDataSource.getRecentSearch().map { it.map { i -> i.toRecentSearch() } }

    override suspend fun insertRecentSearch(keyword: RecentSearch) {
        recentSearchDataSource.insertRecentSearch(keyword.toRecentSearchDto())
    }
}