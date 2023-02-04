package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class NoticeDto(
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("fileId")
    val fileId: FileDto?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)