package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("user")
    val user: UserDto
)