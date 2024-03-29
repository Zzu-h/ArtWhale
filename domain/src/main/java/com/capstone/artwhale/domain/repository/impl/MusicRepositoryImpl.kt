package com.capstone.artwhale.domain.repository.impl

import android.net.Uri
import com.capstone.artwhale.data.datasource.music.MusicDataSource
import com.capstone.artwhale.domain.mapper.toMusic
import com.capstone.artwhale.domain.mapper.toUpdateLikeDto
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.common.LocalPathUtil
import java.io.File
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val localPathUtil: LocalPathUtil,
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
        musicDataSource.getLikeMusicList().map { it.toMusic().copy(isLike = true) }

    override suspend fun updateLikeMusic(id: Int) =
        musicDataSource.updateLikeMusic(id.toUpdateLikeDto())

    override suspend fun registerMusic(
        uri: Uri,
        title: String,
        mood: String,
        lyrics: String,
        albumArtId: Int
    ) {
        localPathUtil.getRealPathFromUri(uri)
            ?.let {
                musicDataSource.registerMusic(
                    "music",
                    File(it),
                    title,
                    mood,
                    lyrics,
                    albumArtId
                )
            }
    }
}