package com.capstone.artwhale.data.dto

import com.capstone.artwhale.BuildConfig.BASE_URL
import com.capstone.artwhale.domain.model.Music
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
) {
    fun toMusic(): Music = Music(
        id = id,
        albumImgUrl = BASE_URL + albumArtId.fileId.path,
        title = title,
        singer = "singer",
        time = duration,
        isLike = like,
        mood = mood,
        musicUrl = BASE_URL + fileId.path,
    )
}