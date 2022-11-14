package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.service.MusicService
import javax.inject.Inject

class MusicDataSourceImpl @Inject constructor(
    private val musicService: MusicService
) : MusicDataSource {
}