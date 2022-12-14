package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.AiTempImage
import com.capstone.artwhale.domain.model.Album

interface AlbumRepository {

    suspend fun getAlbumRanking(): List<Album>
    suspend fun getAllAlbum(): List<Album>
    suspend fun getMyAlbumList(): List<Album>
    suspend fun getLikeAlbumList(): List<Album>
    suspend fun getAiAlbumImageList(): List<AiTempImage>
    suspend fun updateLikeAlbum(id: Int)
    suspend fun registerAlbum(uri: String, title: String, mood: String)
}