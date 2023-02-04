package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Mood

interface MoodRepository {

    suspend fun getMoodList(): List<Mood>
}