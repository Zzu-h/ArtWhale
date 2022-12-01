package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Album

interface AlbumRepository {

    suspend fun getAlbumRanking(): List<Album>
    suspend fun getAllAlbum(): List<Album>
    suspend fun getMyAlbumList(): List<Album>
    suspend fun getLikeAlbumList(): List<Album>
    suspend fun getAiAlbumImageList(): List<String>
    suspend fun updateLikeAlbum(id: Int)
}