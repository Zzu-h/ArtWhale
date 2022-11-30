package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.UpdateNickNameDto
import com.capstone.artwhale.data.dto.UserDto
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserService {

    @GET("/api/user/info")
    suspend fun getMyInfo(): UserDto

    @Multipart
    @PATCH("/api/user/image")
    suspend fun updateProfileImage(@Part image: MultipartBody.Part)

    @PATCH("/api/user/nickname")
    suspend fun updateNickName(@Body nickName: UpdateNickNameDto)
}