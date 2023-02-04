package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.dto.MoodDto
import com.capstone.artwhale.domain.model.Mood

fun MoodDto.toMood(): Mood = Mood(
    id = id,
    name = text,
)