package com.capstone.artwhale.domain.repository.impl

import com.capstone.artwhale.data.datasource.mood.MoodDataSource
import com.capstone.artwhale.domain.mapper.toMood
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.repository.MoodRepository
import javax.inject.Inject

class MoodRepositoryImpl @Inject constructor(
    private val moodDataSource: MoodDataSource
) : MoodRepository {

    override suspend fun getMoodList(): List<Mood> =
        moodDataSource.getAllMoodList().map { it.toMood() }
}