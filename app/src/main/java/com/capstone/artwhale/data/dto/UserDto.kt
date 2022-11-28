package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("email")
    val email: String
)

fun String.toUserDto(): UserDto = UserDto(this)