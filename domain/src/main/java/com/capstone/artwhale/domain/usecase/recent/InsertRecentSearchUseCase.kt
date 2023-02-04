package com.capstone.artwhale.domain.usecase.recent

interface InsertRecentSearchUseCase {

    suspend operator fun invoke(keyword: String): Result<Unit>
}