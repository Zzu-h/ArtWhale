package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.domain.model.Album

interface AlbumDataSource {

    suspend fun getMyAlbumList(): List<Album>
    suspend fun getLikeAlbumList(): List<Album>
    suspend fun updateLikeAlbum(updateLikeDto: UpdateLikeDto)
}