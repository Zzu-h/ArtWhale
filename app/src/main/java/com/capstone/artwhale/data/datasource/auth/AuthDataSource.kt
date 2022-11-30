package com.capstone.artwhale.data.datasource.auth

import com.capstone.artwhale.data.dto.AuthDto
import com.capstone.artwhale.data.dto.LoginDto

interface AuthDataSource {

    suspend fun login(userDto: LoginDto): AuthDto
    suspend fun getTokenInfo(): AuthDto
}