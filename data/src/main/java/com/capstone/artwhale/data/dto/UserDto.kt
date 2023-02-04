package com.capstone.artwhale.data.dto


import com.capstone.artwhale.BuildConfig.BASE_URL
import com.capstone.artwhale.domain.model.UserInfo
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fileId")
    val fileId: FileDto?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("image_url")
    val profileImgUrl: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String
) {
    fun toUserInfo(): UserInfo = UserInfo(
        email = email,
        name = nickname,
        profileImgUrl = if (fileId == null) null else BASE_URL + fileId.path
    )
}