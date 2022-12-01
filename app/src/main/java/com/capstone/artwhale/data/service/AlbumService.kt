package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface AlbumService {

    @GET("/api/album-art")
    suspend fun getAllAlbumList(): List<AlbumDto>

    @GET("/api/album-art/my")
    suspend fun getMyAlbumList(): List<AlbumDto>

    @GET("/api/album-art/like")
    suspend fun getLikeAlbumList(): List<AlbumDto>

    @PATCH("/api/album-art/like")
    suspend fun updateLikeAlbum(@Body updateLikeDto: UpdateLikeDto)
}