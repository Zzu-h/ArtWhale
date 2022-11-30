package com.capstone.artwhale.data.datasource.user

import com.capstone.artwhale.data.dto.UserDto

interface UserDataSource {

    suspend fun getMyInfo(): UserDto
}