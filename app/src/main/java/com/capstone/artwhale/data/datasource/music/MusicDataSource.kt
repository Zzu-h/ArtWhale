package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.data.dto.UpdateLikeDto

interface MusicDataSource {

    suspend fun getAllMusicList(): List<MusicDto>
    suspend fun getMyMusicList(): List<MusicDto>
    suspend fun getLikeMusicList(): List<MusicDto>
    suspend fun updateLikeMusic(updateLikeDto: UpdateLikeDto)
}