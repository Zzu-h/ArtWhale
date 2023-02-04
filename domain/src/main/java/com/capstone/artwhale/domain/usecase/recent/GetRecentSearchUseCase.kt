package com.capstone.artwhale.domain.usecase.recent

import com.capstone.artwhale.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface GetRecentSearchUseCase {

    suspend operator fun invoke(): Result<Flow<List<RecentSearch>>>
}