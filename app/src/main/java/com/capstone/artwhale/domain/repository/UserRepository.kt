package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.UserInfo

interface UserRepository {

    suspend fun getMyInfo(): UserInfo
}