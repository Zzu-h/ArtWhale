package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.AuthDto
import com.capstone.artwhale.data.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/login")
    suspend fun login(@Body userDto: UserDto): AuthDto
}