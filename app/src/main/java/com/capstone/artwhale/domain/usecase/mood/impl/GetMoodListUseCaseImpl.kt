package com.capstone.artwhale.domain.usecase.mood.impl

import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.repository.MoodRepository
import com.capstone.artwhale.domain.usecase.mood.GetMoodListUseCase
import javax.inject.Inject

class GetMoodListUseCaseImpl @Inject constructor(
    private val moodRepository: MoodRepository
) : GetMoodListUseCase {

    override suspend operator fun invoke(): Result<List<Mood>> =
        runCatching { moodRepository.getMoodList() }
}