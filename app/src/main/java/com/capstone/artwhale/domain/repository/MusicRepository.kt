package com.capstone.artwhale.domain.repository

import android.net.Uri
import com.capstone.artwhale.domain.model.Music

interface MusicRepository {

    suspend fun getMusicChart(): List<Music>
    suspend fun getNewMusics(): List<Music>
    suspend fun getMyMusicList(): List<Music>
    suspend fun getLikeMusicList(): List<Music>
    suspend fun updateLikeMusic(id: Int)
    suspend fun registerMusic(
        uri: Uri,
        title: String,
        mood: String,
        lyrics: String,
        albumArtId: Int
    )
}