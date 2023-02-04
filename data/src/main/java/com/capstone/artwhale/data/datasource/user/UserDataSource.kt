package com.capstone.artwhale.data.datasource.user

import com.capstone.artwhale.data.dto.UpdateNickNameDto
import com.capstone.artwhale.data.dto.UserDto
import java.io.File

interface UserDataSource {

    suspend fun getMyInfo(): UserDto
    suspend fun updateProfileImage(key: String, image: File)
    suspend fun updateNickName(nickName: UpdateNickNameDto)
}