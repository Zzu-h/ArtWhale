package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface AlbumService {

    @GET("/api/album-art")
    suspend fun getAllAlbumList(): List<AlbumDto>

    @GET("/api/album-art/my")
    suspend fun getMyAlbumList(): List<AlbumDto>

    @GET("/api/album-art/like")
    suspend fun getLikeAlbumList(): List<AlbumDto>

    @PATCH("/api/album-art/like")
    suspend fun updateLikeAlbum(@Body updateLikeDto: UpdateLikeDto)

    @Multipart
    @POST("/api/album-art")
    suspend fun registerAlbum(
        @Part image: MultipartBody.Part,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    )
}