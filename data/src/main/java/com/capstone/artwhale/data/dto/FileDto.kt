package com.capstone.artwhale.data.dto


import com.google.gson.annotations.SerializedName

data class FileDto(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("fileType")
    val fileType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("originalName")
    val originalName: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)