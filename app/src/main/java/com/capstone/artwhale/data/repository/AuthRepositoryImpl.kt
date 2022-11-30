package com.capstone.artwhale.data.repository

import android.util.Log
import com.capstone.artwhale.data.datasource.auth.AuthDataSource
import com.capstone.artwhale.data.dto.toUserDto
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
            val data = authDataSource.login(email.toUserDto())
            sharedPreferencesManager.saveJwt(data.accessToken)
            Log.d("Tester", "login: ${sharedPreferencesManager.getJwt()}")
            data.toUserInfo()
        }

    override suspend fun getTokenInfo(): Result<UserInfo> =
        runCatching {
            UserInfo(
                "billie@gmail.com",
                "Billie Eilish",
                "https://avatars.githubusercontent.com/u/27036798?v=4"
            )
        }
}