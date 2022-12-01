package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.music.MusicDataSource
import com.capstone.artwhale.data.dto.toUpdateLikeDto
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {

    override suspend fun getMusicChart(): List<Music> =
        musicDataSource.getAllMusicList().map { it.toMusic() }

    override suspend fun getNewMusics(): List<Music> {
        val list = musicDataSource.getAllMusicList().map { it.toMusic() }.reversed()
        return if (list.size > 5) list.subList(0, 5) else list
    }

    override suspend fun getMyMusicList(): List<Music> =
        musicDataSource.getMyMusicList().map { it.toMusic() }

    override suspend fun getLikeMusicList(): List<Music> =
        musicDataSource.getLikeMusicList().map { it.toMusic() }

    override suspend fun updateLikeMusic(id: Int) =
        musicDataSource.updateLikeMusic(id.toUpdateLikeDto())
}