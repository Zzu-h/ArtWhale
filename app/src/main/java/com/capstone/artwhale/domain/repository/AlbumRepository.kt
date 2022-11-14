package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Album

interface AlbumRepository {

    fun getAlbumRanking(): List<Album>
    fun getAllAlbum(): List<Album>
}