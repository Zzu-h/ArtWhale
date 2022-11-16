package com.capstone.artwhale.domain.usecase.album

import com.capstone.artwhale.domain.model.Album

interface GetMyAlbumListUseCase {

    suspend operator fun invoke(): Result<List<Album>>
}