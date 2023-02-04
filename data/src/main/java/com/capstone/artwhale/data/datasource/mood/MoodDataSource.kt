package com.capstone.artwhale.data.datasource.mood

import com.capstone.artwhale.data.dto.MoodDto

interface MoodDataSource {

    suspend fun getAllMoodList(): List<MoodDto>
}