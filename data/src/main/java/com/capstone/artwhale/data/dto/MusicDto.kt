package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class MusicDto(
    @SerializedName("albumArtId")
    val albumArtId: AlbumDto,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("duration")
    val duration: Long,
    @SerializedName("fileId")
    val fileId: FileDto,
    @SerializedName("id")
    val id: Int,
    @SerializedName("like")
    val like: Boolean,
    @SerializedName("lyrics")
    val lyrics: String,
    @SerializedName("mood")
    val mood: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)