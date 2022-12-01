package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.data.service.AlbumService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class AlbumDataSourceImpl @Inject constructor(
    private val albumService: AlbumService
) : AlbumDataSource {

    override suspend fun getAllAlbumList(): List<AlbumDto> = albumService.getAllAlbumList()
    override suspend fun getMyAlbumList(): List<AlbumDto> = albumService.getMyAlbumList()
    override suspend fun getLikeAlbumList(): List<AlbumDto> = albumService.getLikeAlbumList()
    override suspend fun updateLikeAlbum(updateLikeDto: UpdateLikeDto) =
        albumService.updateLikeAlbum(updateLikeDto)

    override suspend fun registerAlbum(key: String, image: File, title: String, mood: String) {
        if (image.extension == "jpg" || image.extension == "jpeg" || image.extension == "png") {
            val requestBody: MutableMap<String, RequestBody> = mutableMapOf()
            requestBody["title"] = title.toRequestBody("text/plain".toMediaTypeOrNull())
            requestBody["mood"] = mood.toRequestBody("text/plain".toMediaTypeOrNull())

            val type = "image/" + image.extension
            albumService.registerAlbum(
                MultipartBody.Part.createFormData(
                    key,
                    image.name,
                    image.asRequestBody(type.toMediaTypeOrNull())
                ),
                requestBody
            )
        }
    }
}