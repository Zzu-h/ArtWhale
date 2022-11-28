package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.UserInfo

interface AuthRepository {

    suspend fun login(email: String): Result<UserInfo>
    suspend fun getMyInfo(): Result<UserInfo>
}