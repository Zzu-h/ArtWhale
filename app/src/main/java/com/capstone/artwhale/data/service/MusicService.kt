package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.domain.model.Music
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MusicService {

    @GET("/api/music/my")
    suspend fun getMyMusicList(): List<Music>

    @GET("/api/music/like")
    suspend fun getLikeMusicList(): List<Music>

    @PATCH("/api/music/like")
    suspend fun updateLikeMusic(@Body updateLikeDto: UpdateLikeDto)
}