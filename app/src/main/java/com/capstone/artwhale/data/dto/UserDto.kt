package com.capstone.artwhale.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("email")
    val email: String
)

fun String.toUserDto(): UserDto = UserDto(this)