package com.capstone.artwhale.domain.usecase.album.impl

import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.domain.usecase.album.UpdateLikeAlbumUseCase
import javax.inject.Inject

class UpdateLikeAlbumUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : UpdateLikeAlbumUseCase {

    override suspend fun invoke(id: Int): Result<Unit> =
        runCatching { albumRepository.updateLikeAlbum(id) }
}