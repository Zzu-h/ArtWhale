package com.capstone.artwhale.data.dto

data class UserDto(val email: String)

fun String.toUserDto(): UserDto = UserDto(this)