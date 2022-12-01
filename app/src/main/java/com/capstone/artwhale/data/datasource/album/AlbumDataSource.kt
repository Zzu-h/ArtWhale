package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.data.dto.UpdateLikeDto

interface AlbumDataSource {

    suspend fun getAllAlbumList(): List<AlbumDto>
    suspend fun getMyAlbumList(): List<AlbumDto>
    suspend fun getLikeAlbumList(): List<AlbumDto>
    suspend fun updateLikeAlbum(updateLikeDto: UpdateLikeDto)
}