package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.BuildConfig.BASE_URL
import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.domain.model.Music

fun MusicDto.toMusic(): Music = Music(
    id = id,
    albumImgUrl = BASE_URL + albumArtId.fileId.path,
    title = title,
    singer = "singer",
    time = duration,
    isLike = like,
    mood = mood,
    musicUrl = BASE_URL + fileId.path,
)