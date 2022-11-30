package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.AuthDto
import com.capstone.artwhale.data.dto.LoginDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("/api/auth/login")
    suspend fun login(@Body loginDto: LoginDto): AuthDto

    @GET("/api/auth/tokenInfo")
    suspend fun getTokenInfo(): AuthDto
}