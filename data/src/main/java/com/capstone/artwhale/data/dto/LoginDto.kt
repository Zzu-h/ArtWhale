package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("email")
    val email: String
)