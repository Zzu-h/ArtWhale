package com.capstone.artwhale.data.service

import com.capstone.artwhale.domain.model.Album
import retrofit2.http.GET

interface AlbumService {

    @GET("/api/album-art/my")
    suspend fun getMyAlbumList(): List<Album>

    @GET("/api/album-art/like")
    suspend fun getLikeAlbumList(): List<Album>
}