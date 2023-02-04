package com.capstone.artwhale.domain.usecase.recent.impl

import com.capstone.artwhale.domain.model.RecentSearch
import com.capstone.artwhale.domain.repository.RecentSearchRepository
import com.capstone.artwhale.domain.usecase.recent.GetRecentSearchUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentSearchUseCaseImpl @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) : GetRecentSearchUseCase {

    override suspend operator fun invoke(): Result<Flow<List<RecentSearch>>> =
        runCatching { recentSearchRepository.getRecentSearch() }
}