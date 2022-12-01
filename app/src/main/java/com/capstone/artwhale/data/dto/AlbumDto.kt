package com.capstone.artwhale.data.dto


import com.capstone.artwhale.BuildConfig.BASE_URL
import com.capstone.artwhale.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("fileId")
    val fileId: FileDto,
    @SerializedName("id")
    val id: Int,
    @SerializedName("like")
    val like: Boolean,
    @SerializedName("method")
    val method: String,
    @SerializedName("mood")
    val mood: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
) {
    fun toAlbum(): Album = Album(
        id = id,
        albumImgUrl = BASE_URL + fileId.path,
        title = title,
        mood = mood,
        isLike = like,
    )
}