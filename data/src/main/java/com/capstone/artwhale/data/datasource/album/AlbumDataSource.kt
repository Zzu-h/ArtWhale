package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import java.io.File

interface AlbumDataSource {

    suspend fun getAllAlbumList(): List<AlbumDto>
    suspend fun getMyAlbumList(): List<AlbumDto>
    suspend fun getLikeAlbumList(): List<AlbumDto>
    suspend fun updateLikeAlbum(updateLikeDto: UpdateLikeDto)
    suspend fun registerAlbum(key: String, file: File, title: String, mood: String)
}