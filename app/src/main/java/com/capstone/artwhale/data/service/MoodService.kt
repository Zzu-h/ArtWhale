package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.MoodDto
import retrofit2.http.GET

interface MoodService {

    @GET("/api/mood")
    fun getAllMoodList(): List<MoodDto>
}