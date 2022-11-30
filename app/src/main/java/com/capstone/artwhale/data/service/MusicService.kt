package com.capstone.artwhale.data.service

import com.capstone.artwhale.domain.model.Music
import retrofit2.http.GET

interface MusicService {

    @GET("/api/music/my")
    suspend fun getMyMusicList(): List<Music>

    @GET("/api/music/like")
    suspend fun getLikeMusicList(): List<Music>
}