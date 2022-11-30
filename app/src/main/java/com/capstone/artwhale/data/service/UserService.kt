package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.UserDto
import retrofit2.http.POST

interface UserService {

    @POST("/api/user/info")
    suspend fun getMyInfo(): UserDto
}