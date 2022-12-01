package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.dto.UpdateLikeDto
import com.capstone.artwhale.data.service.AlbumService
import com.capstone.artwhale.domain.model.Album
import javax.inject.Inject

class AlbumDataSourceImpl @Inject constructor(
    private val albumService: AlbumService
) : AlbumDataSource {

    override suspend fun getMyAlbumList(): List<Album> = albumService.getMyAlbumList()
    override suspend fun getLikeAlbumList(): List<Album> = albumService.getLikeAlbumList()
    override suspend fun updateLikeAlbum(updateLikeDto: UpdateLikeDto) =
        albumService.updateLikeAlbum(updateLikeDto)
}