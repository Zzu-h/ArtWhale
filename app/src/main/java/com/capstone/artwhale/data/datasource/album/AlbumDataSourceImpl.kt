package com.capstone.artwhale.data.datasource.album

import com.capstone.artwhale.data.service.AlbumService
import javax.inject.Inject

class AlbumDataSourceImpl @Inject constructor(
    private val albumService: AlbumService
) : AlbumDataSource {}