package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MusicService {

    @GET("/api/music")
    suspend fun getAllMusicList(): List<MusicDto>

    @GET("/api/music/my")
    suspend fun getMyMusicList(): List<MusicDto>

    @GET("/api/music/like")
    suspend fun getLikeMusicList(): List<MusicDto>

    @PATCH("/api/music/like")
    suspend fun updateLikeMusic(@Body updateLikeDto: UpdateLikeDto)
}