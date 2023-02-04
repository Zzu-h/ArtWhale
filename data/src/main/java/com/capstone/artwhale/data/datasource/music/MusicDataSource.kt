package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import java.io.File

interface MusicDataSource {

    suspend fun getAllMusicList(): List<MusicDto>
    suspend fun getMyMusicList(): List<MusicDto>
    suspend fun getLikeMusicList(): List<MusicDto>
    suspend fun updateLikeMusic(updateLikeDto: UpdateLikeDto)
    suspend fun registerMusic(
        s: String,
        file: File,
        title: String,
        mood: String,
        lyrics: String,
        albumArtId: Int
    )
}