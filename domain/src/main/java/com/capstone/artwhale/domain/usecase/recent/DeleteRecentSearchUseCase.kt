package com.capstone.artwhale.domain.usecase.recent

import com.capstone.artwhale.domain.model.RecentSearch

interface DeleteRecentSearchUseCase {

    suspend operator fun invoke(keyword: RecentSearch): Result<Unit>
}