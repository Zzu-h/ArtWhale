package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.domain.model.Album
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface AlbumService {

    @GET("/api/album-art/my")
    suspend fun getMyAlbumList(): List<Album>

    @GET("/api/album-art/like")
    suspend fun getLikeAlbumList(): List<Album>

    @PATCH("/api/album-art/like")
    suspend fun updateLikeAlbum(@Body updateLikeDto: UpdateLikeDto)
}