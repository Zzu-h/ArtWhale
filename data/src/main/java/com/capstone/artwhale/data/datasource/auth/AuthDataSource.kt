package com.capstone.artwhale.data.datasource.auth

import com.capstone.artwhale.data.dto.AuthDto
import com.capstone.artwhale.data.dto.LoginDto
import com.capstone.artwhale.data.dto.TokenInfoDto

interface AuthDataSource {

    suspend fun login(userDto: LoginDto): AuthDto
    suspend fun getTokenInfo(): TokenInfoDto
}