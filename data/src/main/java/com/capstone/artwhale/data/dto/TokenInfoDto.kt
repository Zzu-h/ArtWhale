package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class TokenInfoDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("expiresIn")
    val expiresIn: Long
)