package com.capstone.artwhale.data.dto


import com.google.gson.annotations.SerializedName

data class MoodDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String
)