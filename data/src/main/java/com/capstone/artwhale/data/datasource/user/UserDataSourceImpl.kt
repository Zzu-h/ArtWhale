package com.capstone.artwhale.data.datasource.user

import com.capstone.artwhale.data.dto.UpdateNickNameDto
import com.capstone.artwhale.data.dto.UserDto
import com.capstone.artwhale.data.service.UserService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {

    override suspend fun getMyInfo(): UserDto = userService.getMyInfo()

    override suspend fun updateNickName(nickName: UpdateNickNameDto) =
        userService.updateNickName(nickName)

    override suspend fun updateProfileImage(key: String, image: File) {
        if (image.extension == "jpg" || image.extension == "jpeg" || image.extension == "png") {
            val type = "image/" + image.extension
            userService.updateProfileImage(
                MultipartBody.Part.createFormData(
                    key,
                    image.name,
                    image.asRequestBody(type.toMediaTypeOrNull())
                )
            )
        }
    }
}