package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.service.MusicService
import com.capstone.artwhale.domain.model.Music
import javax.inject.Inject

class MusicDataSourceImpl @Inject constructor(
    private val musicService: MusicService
) : MusicDataSource {

    override suspend fun getMyMusicList(): List<Music> = musicService.getMyMusicList()
    override suspend fun getLikeMusicList(): List<Music> = musicService.getLikeMusicList()
}