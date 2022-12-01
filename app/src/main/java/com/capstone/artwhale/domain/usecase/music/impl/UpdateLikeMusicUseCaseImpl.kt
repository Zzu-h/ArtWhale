package com.capstone.artwhale.domain.usecase.music.impl

import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.UpdateLikeMusicUseCase
import javax.inject.Inject

class UpdateLikeMusicUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : UpdateLikeMusicUseCase {

    override suspend fun invoke(id: Int): Result<Unit> =
        runCatching { repository.updateLikeMusic(id) }
}