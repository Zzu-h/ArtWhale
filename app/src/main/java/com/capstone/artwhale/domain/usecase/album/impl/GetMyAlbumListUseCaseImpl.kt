package com.capstone.artwhale.domain.usecase.album.impl

import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.domain.usecase.album.GetMyAlbumListUseCase
import javax.inject.Inject

class GetMyAlbumListUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetMyAlbumListUseCase {

    override suspend fun invoke(): Result<List<Album>> =
        runCatching { albumRepository.getMyAlbumList() }
}