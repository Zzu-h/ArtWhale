package com.capstone.artwhale.data.datasource.auth

import com.capstone.artwhale.data.dto.AuthDto
import com.capstone.artwhale.data.dto.UserDto

interface AuthDataSource {

    suspend fun login(userDto: UserDto): AuthDto
}