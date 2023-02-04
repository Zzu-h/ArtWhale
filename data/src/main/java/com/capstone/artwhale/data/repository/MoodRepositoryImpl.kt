package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.mood.MoodDataSource
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.repository.MoodRepository
import javax.inject.Inject

class MoodRepositoryImpl @Inject constructor(
    private val moodDataSource: MoodDataSource
) : MoodRepository {

    override suspend fun getMoodList(): List<Mood> =
        moodDataSource.getAllMoodList().map { it.toMood() }
}