package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.BuildConfig.BASE_URL
import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.domain.model.Album

fun AlbumDto.toAlbum(): Album = Album(
    id = id,
    albumImgUrl = BASE_URL + fileId.path,
    title = title,
    mood = mood,
    isLike = like,
)