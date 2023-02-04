package com.capstone.artwhale.data.dto


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
)