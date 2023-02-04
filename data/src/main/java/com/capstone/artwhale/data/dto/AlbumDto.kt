package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("fileId")
    val fileId: FileDto,
    @SerializedName("id")
    val id: Int,
    @SerializedName("like")
    val like: Boolean = false,
    @SerializedName("method")
    val method: String,
    @SerializedName("mood")
    val mood: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)