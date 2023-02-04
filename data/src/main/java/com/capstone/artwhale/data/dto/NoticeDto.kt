package com.capstone.artwhale.data.dto

import com.capstone.artwhale.BuildConfig
import com.capstone.artwhale.domain.model.Notice
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
) {
    fun toNotice(): Notice = Notice(
        topText = title,
        contentText = content,
        imageUrl = if (fileId == null) null else BuildConfig.BASE_URL + fileId.path
    )
}