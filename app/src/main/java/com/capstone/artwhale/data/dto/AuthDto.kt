package com.capstone.artwhale.data.dto

import com.capstone.artwhale.domain.model.UserInfo
import kotlinx.serialization.SerialName

data class AuthDto(
    @SerialName("email") val email: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("image_url") val profileImgUrl: String? = null,
    @SerialName("access_token") val accessToken: String,
) {
    fun toUserInfo(): UserInfo = UserInfo(
        email = email,
        name = nickname,
        profileImgUrl = profileImgUrl
    )
}