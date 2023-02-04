package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class UpdateNickNameDto(
    @SerializedName("nickname")
    val nickname: String
)