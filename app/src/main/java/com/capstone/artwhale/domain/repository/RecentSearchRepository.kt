package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {

    suspend fun getRecentSearch(): Flow<List<RecentSearch>>
}