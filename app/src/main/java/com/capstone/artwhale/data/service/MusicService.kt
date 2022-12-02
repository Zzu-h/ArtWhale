package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface MusicService {

    @GET("/api/music")
    suspend fun getAllMusicList(): List<MusicDto>

    @GET("/api/music/my")
    suspend fun getMyMusicList(): List<MusicDto>

    @GET("/api/music/like")
    suspend fun getLikeMusicList(): List<MusicDto>

    @PATCH("/api/music/like")
    suspend fun updateLikeMusic(@Body updateLikeDto: UpdateLikeDto)

    @Multipart
    @POST("/api/music")
    suspend fun registerMusic(
        @Part image: MultipartBody.Part,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    )
}