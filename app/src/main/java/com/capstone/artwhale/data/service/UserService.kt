package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.UserDto
import retrofit2.http.GET

interface UserService {

    @GET("/api/user/info")
    suspend fun getMyInfo(): UserDto
}