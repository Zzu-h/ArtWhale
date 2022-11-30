package com.capstone.artwhale.data.datasource.user

import com.capstone.artwhale.data.dto.UserDto
import com.capstone.artwhale.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {

    override suspend fun getMyInfo(): UserDto = userService.getMyInfo()
}