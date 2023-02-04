package com.capstone.artwhale.data.datasource.auth

import com.capstone.artwhale.data.dto.AuthDto
import com.capstone.artwhale.data.dto.LoginDto
import com.capstone.artwhale.data.dto.TokenInfoDto
import com.capstone.artwhale.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override suspend fun login(userDto: LoginDto): AuthDto = authService.login(userDto)
    override suspend fun getTokenInfo(): TokenInfoDto = authService.getTokenInfo()
}