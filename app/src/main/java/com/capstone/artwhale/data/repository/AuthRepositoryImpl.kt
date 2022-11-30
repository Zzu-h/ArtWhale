package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.auth.AuthDataSource
import com.capstone.artwhale.data.dto.toLoginDto
import com.capstone.artwhale.domain.model.TokenInfo
import com.capstone.artwhale.domain.model.UserInfo
import com.capstone.artwhale.domain.repository.AuthRepository
import com.capstone.artwhale.util.SharedPreferencesManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val sharedPreferencesManager: SharedPreferencesManager
) : AuthRepository {

    override suspend fun login(email: String): Result<UserInfo> =
        runCatching {
            val data = authDataSource.login(email.toLoginDto())
            sharedPreferencesManager.saveJwt(data.accessToken)
            data.user.toUserInfo()
        }

    override suspend fun getTokenInfo(): Result<TokenInfo> =
        runCatching { authDataSource.getTokenInfo().toTokenInfo() }
}