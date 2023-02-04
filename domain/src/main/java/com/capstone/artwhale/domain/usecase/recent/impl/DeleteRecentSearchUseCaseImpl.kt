package com.capstone.artwhale.domain.usecase.recent.impl

import com.capstone.artwhale.domain.model.RecentSearch
import com.capstone.artwhale.domain.repository.RecentSearchRepository
import com.capstone.artwhale.domain.usecase.recent.DeleteRecentSearchUseCase
import javax.inject.Inject

class DeleteRecentSearchUseCaseImpl @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) : DeleteRecentSearchUseCase {

    override suspend operator fun invoke(keyword: RecentSearch): Result<Unit> =
        runCatching { recentSearchRepository.deleteRecentSearch(keyword) }
}