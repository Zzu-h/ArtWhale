package com.capstone.artwhale.domain.usecase.album.impl

import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.domain.usecase.album.RegisterAlbumUseCase
import javax.inject.Inject

class RegisterAlbumUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : RegisterAlbumUseCase {

    override suspend operator fun invoke(uri: String, title: String, mood: String): Result<Unit> =
        runCatching { albumRepository.registerAlbum(uri, title, mood) }
}