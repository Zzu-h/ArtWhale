package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.domain.model.Music

interface MusicDataSource {

    suspend fun getMyMusicList(): List<Music>
    suspend fun getLikeMusicList(): List<Music>
    suspend fun updateLikeMusic(updateLikeDto: UpdateLikeDto)
}