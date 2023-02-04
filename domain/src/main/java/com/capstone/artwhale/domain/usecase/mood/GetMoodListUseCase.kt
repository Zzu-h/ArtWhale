package com.capstone.artwhale.domain.usecase.mood

import com.capstone.artwhale.domain.model.Mood

interface GetMoodListUseCase {

    suspend operator fun invoke(): Result<List<Mood>>
}