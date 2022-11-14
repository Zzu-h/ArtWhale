package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Music

interface MusicRepository {

    suspend fun getMusicChart(): List<Music>
    suspend fun getNewMusics(): List<Music>
    suspend fun getMyMusicList(): List<Music>
    suspend fun getLikeMusicList(): List<Music>
}