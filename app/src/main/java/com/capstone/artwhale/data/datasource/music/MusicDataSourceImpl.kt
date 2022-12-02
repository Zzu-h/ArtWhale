package com.capstone.artwhale.data.datasource.music

import com.capstone.artwhale.data.dto.MusicDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.data.service.MusicService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class MusicDataSourceImpl @Inject constructor(
    private val musicService: MusicService
) : MusicDataSource {

    override suspend fun getAllMusicList(): List<MusicDto> = musicService.getAllMusicList()
    override suspend fun getMyMusicList(): List<MusicDto> = musicService.getMyMusicList()
    override suspend fun getLikeMusicList(): List<MusicDto> = musicService.getLikeMusicList()
    override suspend fun updateLikeMusic(updateLikeDto: UpdateLikeDto) =
        musicService.updateLikeMusic(updateLikeDto)

    override suspend fun registerMusic(
        key: String,
        music: File,
        title: String,
        mood: String,
        lyrics: String,
        albumArtId: Int
    ) {
        val requestBody: MutableMap<String, RequestBody> = mutableMapOf()
        requestBody["title"] = title.toRequestBody("text/plain".toMediaTypeOrNull())
        requestBody["mood"] = mood.toRequestBody("text/plain".toMediaTypeOrNull())
        requestBody["lyrics"] = lyrics.toRequestBody("text/plain".toMediaTypeOrNull())
        requestBody["albumArtId"] =
            albumArtId.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val type = "audio/*"
        musicService.registerMusic(
            MultipartBody.Part.createFormData(
                key,
                music.name,
                music.asRequestBody(type.toMediaTypeOrNull())
            ),
            requestBody
        )
    }
}