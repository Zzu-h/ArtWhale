package com.capstone.artwhale.domain.usecase.album

import com.capstone.artwhale.domain.model.Album

interface GetAllAlbumUseCase {

    suspend operator fun invoke(): Result<List<Album>>
}