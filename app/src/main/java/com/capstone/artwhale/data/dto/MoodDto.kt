package com.capstone.artwhale.data.dto


import com.capstone.artwhale.domain.model.Mood
import com.google.gson.annotations.SerializedName

data class MoodDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String
) {
    fun toMood(): Mood = Mood(
        id = id,
        name = text,
    )
}