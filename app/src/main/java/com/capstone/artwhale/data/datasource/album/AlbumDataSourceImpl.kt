package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.dto.AlbumDto
import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.data.service.AlbumService
import javax.inject.Inject

class AlbumDataSourceImpl @Inject constructor(
    private val albumService: AlbumService
) : AlbumDataSource {

    override suspend fun getAllAlbumList(): List<AlbumDto> = albumService.getAllAlbumList()
    override suspend fun getMyAlbumList(): List<AlbumDto> = albumService.getMyAlbumList()
    override suspend fun getLikeAlbumList(): List<AlbumDto> = albumService.getLikeAlbumList()
    override suspend fun updateLikeAlbum(updateLikeDto: UpdateLikeDto) =
        albumService.updateLikeAlbum(updateLikeDto)
}