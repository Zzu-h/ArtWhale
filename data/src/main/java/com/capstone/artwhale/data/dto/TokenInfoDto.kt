package com.capstone.artwhale.data.dto

import com.capstone.artwhale.domain.model.TokenInfo
import com.google.gson.annotations.SerializedName

data class TokenInfoDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("expiresIn")
    val expiresIn: Long
) {
    fun toTokenInfo(): TokenInfo = TokenInfo(
        email = email,
        nickname = nickname,
        expiresIn = expiresIn
    )
}