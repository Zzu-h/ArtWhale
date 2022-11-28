package com.capstone.artwhale.data.dto

import com.capstone.artwhale.domain.model.UserInfo
import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("email") val email: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("image_url") val profileImgUrl: String? = null,
    @SerializedName("access_token") val accessToken: String,
) {
    fun toUserInfo(): UserInfo = UserInfo(
        email = email,
        name = nickname,
        profileImgUrl = profileImgUrl
    )
}