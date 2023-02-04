package com.capstone.artwhale.domain.usecase.recent.impl

import com.capstone.artwhale.domain.model.RecentSearch
import com.capstone.artwhale.domain.repository.RecentSearchRepository
import com.capstone.artwhale.domain.usecase.recent.InsertRecentSearchUseCase
import javax.inject.Inject

class InsertRecentSearchUseCaseImpl @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) : InsertRecentSearchUseCase {

    override suspend operator fun invoke(keyword: String): Result<Unit> =
        runCatching { recentSearchRepository.insertRecentSearch(RecentSearch(keyword)) }
}