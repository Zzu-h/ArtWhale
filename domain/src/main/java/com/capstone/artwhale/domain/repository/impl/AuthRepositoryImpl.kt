package com.capstone.artwhale.domain.repository.impl

import com.capstone.artwhale.data.datasource.auth.AuthDataSource
import com.capstone.artwhale.domain.mapper.toLoginDto
import com.capstone.artwhale.domain.mapper.toTokenInfo
import com.capstone.artwhale.domain.mapper.toUserInfo
import com.capstone.artwhale.domain.model.TokenInfo
import com.capstone.artwhale.domain.model.UserInfo
import com.capstone.artwhale.domain.repository.AuthRepository
import com.capstone.artwhale.common.SharedPreferencesManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val sharedPreferencesManager: com.capstone.artwhale.common.SharedPreferencesManager
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