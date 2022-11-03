package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.auth.AuthDataSource
import com.capstone.artwhale.data.dto.toUserDto
import com.capstone.artwhale.domain.model.Auth
import com.capstone.artwhale.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun login(email: String): Result<Auth> =
        runCatching { authDataSource.login(email.toUserDto()).toAuth() }
}