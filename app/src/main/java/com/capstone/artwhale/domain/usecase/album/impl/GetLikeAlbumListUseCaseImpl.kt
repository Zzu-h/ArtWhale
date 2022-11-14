package com.capstone.artwhale.domain.usecase.album.impl

import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.domain.usecase.album.GetAllAlbumUseCase
import com.capstone.artwhale.domain.usecase.album.GetLikeAlbumListUseCase
import javax.inject.Inject

class GetLikeAlbumListUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetLikeAlbumListUseCase {

    override suspend fun invoke(): Result<List<Album>> =
        runCatching { albumRepository.getAllAlbum() }
}