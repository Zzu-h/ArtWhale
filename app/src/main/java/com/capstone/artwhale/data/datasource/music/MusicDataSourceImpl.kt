package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.data.service.MusicService
import javax.inject.Inject

class MusicDataSourceImpl @Inject constructor(
    private val musicService: MusicService
) : MusicDataSource {

    override suspend fun getAllMusicList(): List<MusicDto> = musicService.getAllMusicList()
    override suspend fun getMyMusicList(): List<MusicDto> = musicService.getMyMusicList()
    override suspend fun getLikeMusicList(): List<MusicDto> = musicService.getLikeMusicList()
    override suspend fun updateLikeMusic(updateLikeDto: UpdateLikeDto) =
        musicService.updateLikeMusic(updateLikeDto)
}