package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Music

interface MusicRepository {

    fun getMusicChart(): List<Music>
    fun getNewMusics(): List<Music>
}